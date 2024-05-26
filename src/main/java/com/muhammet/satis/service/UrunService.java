package com.muhammet.satis.service;

import com.muhammet.satis.repository.UrunRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrunService {

    private final UrunRepository urunRepository;



}
