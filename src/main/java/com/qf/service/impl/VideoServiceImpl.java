package com.qf.service.impl;

import com.qf.dao.VideoMapper;
import com.qf.pojo.QueryVo;
import com.qf.pojo.Video;
import com.qf.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public Video selectVideoById(Integer id) {
        return videoMapper.selectVideoById(id);
    }

//    -----------------------------------------------

    @Override
    public String insert(Video record) {
        videoMapper.insert(record);
        return "/";
    }

    @Override
    public List<Video> selectByExample(Video video) {
        return videoMapper.selectByExample(video);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return videoMapper.deleteByPrimaryKey(id);
    }



    @Override
    public void updateByPrimaryKey(Integer id) {
        videoMapper.updateByPrimaryKey(id);
    }


    @Override
    public List<Video> findAllVideos(QueryVo queryVo) {
        return videoMapper.findAllVideos(queryVo);
    }

    @Override
    public void updatePlayNum(Video video) {
        videoMapper.updateByPrimaryKeySelective(video);
    }


}
