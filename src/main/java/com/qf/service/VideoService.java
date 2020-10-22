package com.qf.service;

import com.qf.pojo.QueryVo;
import com.qf.pojo.Video;

import java.util.List;

public interface VideoService {

    Video selectVideoById(Integer id);

    String insert(Video record);

    List<Video> selectByExample(Video video);

    int deleteByPrimaryKey(Integer id);

    void updateByPrimaryKey(Integer id);

    List<Video> findAllVideos(QueryVo queryVo);
}
