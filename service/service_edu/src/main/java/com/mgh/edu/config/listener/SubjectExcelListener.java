package com.mgh.edu.config.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mgh.edu.entity.Subject;
import com.mgh.edu.entity.excel.SubjectData;
import com.mgh.edu.service.SubjectService;

import java.util.List;
import java.util.Map;

/**
 * @author MGH
 * @create 2022-0216 12:24 下午
 */
public class SubjectExcelListener implements ReadListener<SubjectData> {
    // 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
    private static final int BATCH_COUNT = 100;
    // 缓存的数据
    private List<Subject> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    //service属性添加,new对象时将其传进来
    private SubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    // 读取表头
    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
     /*   Map<Integer, String> map= ConverterUtils.convertToStringMap(headMap, context); //转换成 Map<Integer,String>
        for (Integer i:map.keySet()){
            log.info("解析到一条头数据:{}", map.get(i));
        }*/
    }

    /**
     * 这个每一条数据解析都会来调用
     */
    @Override
    public void invoke(SubjectData data, AnalysisContext context) {
        if (data==null){
            //数据为空返回
            return;
        }
        Subject oneSubject = this.existOneSubject(subjectService, data.getOneSubjectName());
        if (oneSubject==null){
            oneSubject = new Subject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(data.getOneSubjectName());
            subjectService.save(oneSubject); //先将一级分类保存到数据库
        }
        //获取一级分类id
        String pid=oneSubject.getId();
        Subject twoSubject = this.existTwoSubject(subjectService, data.getTwoSubjectName(),pid);
        if (twoSubject==null){
            twoSubject = new Subject();
            twoSubject.setParentId(pid);
            twoSubject.setTitle(data.getTwoSubjectName());
            cachedDataList.add(twoSubject); //二级分类添加到数组等待一起插入到数据库
        }
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
    }

   //批量插入二级分类
    private void saveData() {
        subjectService.saveBatch(cachedDataList); //批量插入二级分类
    }

    /**
     * 判断数据库是否重复一级分类
     */
    private Subject existOneSubject(SubjectService service,String oneSubject) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",oneSubject);
        queryWrapper.eq("parent_id",0);
        Subject one = service.getOne(queryWrapper);
        return one;
    }
    /**
     * 判断数据库是否重复二级分类
     */
    private Subject existTwoSubject(SubjectService service,String towSubject,String pid) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title",towSubject);
        queryWrapper.eq("parent_id",pid);
        Subject two = service.getOne(queryWrapper);
        return two;
    }
}
