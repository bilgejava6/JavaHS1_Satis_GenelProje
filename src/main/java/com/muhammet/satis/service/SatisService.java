package com.muhammet.satis.service;

import com.muhammet.satis.repository.SatisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SatisService {
    /**
     * Kullanımı önerilmez.
     */
    @Autowired
    private SatisRepository satisRepository;
}
