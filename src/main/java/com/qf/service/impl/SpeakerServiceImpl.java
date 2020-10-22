package com.qf.service.impl;

import com.qf.dao.SpeakerMapper;
import com.qf.pojo.Speaker;
import com.qf.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakerServiceImpl implements SpeakerService {

    @Autowired
    private SpeakerMapper speakerMapper;


    @Override
    public Speaker selectByPrimaryKey(Integer id) {
        return speakerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Speaker> findAll() {
        return speakerMapper.findAll();
    }

    @Override
    public void addSpeaker(Speaker speaker) {
        speakerMapper.insert(speaker);
    }

    @Override
    public int delSpeakerById(Integer id) {
       return speakerMapper.deleteByPrimaryKey(id);

    }

    @Override
    public int updateById(Integer id) {
        return speakerMapper.updateByPrimaryKey(id);
    }

    @Override
    public void updateSpeaker(Speaker speaker){
        speakerMapper.updateByPrimaryKeySelective(speaker);
    }
}
