package com.mgh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mgh.cms.entity.EduSubject;
import com.mgh.cms.entity.vo.OneSubject;
import com.mgh.cms.entity.vo.TwoSubject;
import com.mgh.cms.mapper.EduSubjectMapper;
import com.mgh.cms.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author mgh
 * @since 2022-02-26
 */
@Service("eduSubjectService")
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Autowired
    private EduSubjectMapper subjectMapper;

    @Override
    public ArrayList<OneSubject> getAllOneSubject() {
        ArrayList<OneSubject> arrayList = new ArrayList<>();
        QueryWrapper oneWrapper = new QueryWrapper();
        oneWrapper.eq("parent_id", "0");
        oneWrapper.select("id", "title");
        List<EduSubject> oneList = subjectMapper.selectList(oneWrapper);
        for (EduSubject sub : oneList) {
            OneSubject oneSubject = new OneSubject();
            QueryWrapper twoWrapper = new QueryWrapper();
            twoWrapper.eq("parent_id", sub.getId());
            twoWrapper.select("id", "title");
            oneSubject.setId(sub.getId());
            oneSubject.setTitle(sub.getTitle());
            List<EduSubject> twoList = subjectMapper.selectList(twoWrapper);
            ArrayList<TwoSubject> valueList = new ArrayList<>();
            for (EduSubject s : twoList) {
                TwoSubject twoSubject = new TwoSubject(s.getId(), s.getTitle());
                valueList.add(twoSubject);
            }
            oneSubject.setChildren(valueList);
            arrayList.add(oneSubject);
        }
        return arrayList;
    }
}
