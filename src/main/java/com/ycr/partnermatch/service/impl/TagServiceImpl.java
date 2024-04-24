package com.ycr.partnermatch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycr.partnermatch.model.domain.Tag;
import com.ycr.partnermatch.service.TagService;
import com.ycr.partnermatch.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author null&&
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2024-04-17 19:05:44
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




