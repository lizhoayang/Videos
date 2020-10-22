package com.qf.service;

import com.qf.pojo.Speaker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpeakerService {
    Speaker selectByPrimaryKey(Integer id);

    List<Speaker> findAll();

    void addSpeaker(Speaker speaker);

    int delSpeakerById(Integer id);

    int updateById(Integer id);

    void updateSpeaker(Speaker speaker);
}
