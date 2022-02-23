package com.mgh.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgh.edu.entity.Subject;
import com.mgh.edu.entity.vo.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author mgh
 * @since 2022-02-16
 */
public interface SubjectService extends IService<Subject> {

   public void saveSubject(MultipartFile file,SubjectService subjectService);
   public List<OneSubject> getAll();
}
