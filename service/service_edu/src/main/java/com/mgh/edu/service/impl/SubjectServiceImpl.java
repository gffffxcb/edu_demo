package com.mgh.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgh.edu.config.listener.SubjectExcelListener;
import com.mgh.edu.entity.Subject;
import com.mgh.edu.entity.excel.SubjectData;
import com.mgh.edu.entity.vo.OneSubject;
import com.mgh.edu.entity.vo.TwoSubject;
import com.mgh.edu.mapper.SubjectMapper;
import com.mgh.edu.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 * @author mgh
 * @since 2022-02-16
 */
@Service("subjectService")
@Slf4j
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    private SubjectMapper mapper;
    @Override
    public void saveSubject(MultipartFile file,SubjectService service) {
        try {
            InputStream inputStream = file.getInputStream();
            //doReadAll() 读取所有sheet
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(service)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAll() {
        ArrayList<OneSubject> arrayList = new ArrayList<>();
        QueryWrapper oneWrapper = new QueryWrapper();
        oneWrapper.eq("parent_id","0");
        oneWrapper.select("id","title");
        List<Subject> oneList = mapper.selectList(oneWrapper);
        for (Subject sub:oneList) {
            OneSubject oneSubject = new OneSubject();
            QueryWrapper twoWrapper = new QueryWrapper();
            twoWrapper.eq("parent_id",sub.getId());
            twoWrapper.select("id","title");
            oneSubject.setId(sub.getId());
            oneSubject.setTitle(sub.getTitle());
            List<Subject> twoList = mapper.selectList(twoWrapper);
            ArrayList<TwoSubject> valueList = new ArrayList<>();
            for (Subject s:twoList) {
                TwoSubject twoSubject = new TwoSubject(s.getId(),s.getTitle());
                valueList.add(twoSubject);
            }
            oneSubject.setChildren(valueList);
            arrayList.add(oneSubject);
        }
        return arrayList;
    }
}
