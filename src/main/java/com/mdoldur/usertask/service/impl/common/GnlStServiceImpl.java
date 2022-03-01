package com.mdoldur.usertask.service.impl.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdoldur.usertask.entity.GnlStEntity;
import com.mdoldur.usertask.repository.common.GnlStRepository;
import com.mdoldur.usertask.service.interfaces.common.GnlStService;

@Service
public class GnlStServiceImpl implements GnlStService {
    @Autowired
    GnlStRepository gnlStRepository;
    
    public GnlStEntity findByEntCodeNameAndShrtCode(String entCodeName, String shrtCode) {
    	return gnlStRepository.findByEntCodeNameAndShrtCode(entCodeName, shrtCode);
    }
    
}
