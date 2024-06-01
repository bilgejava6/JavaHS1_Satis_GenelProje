package com.muhammet.satis.controller;

import com.muhammet.satis.config.RestApis;
import com.muhammet.satis.dto.request.UrunSaveRequestDto;
import com.muhammet.satis.entity.Satis;
import com.muhammet.satis.entity.Urun;
import com.muhammet.satis.service.UrunService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.muhammet.satis.config.RestApis.*;

@RestController
@RequestMapping(URUN)
@RequiredArgsConstructor
public class UrunController {

    private final UrunService urunService;
    @PostMapping(SAVE)
    public void save(UrunSaveRequestDto dto){
        urunService.save(dto);
    }

    @GetMapping(GETALL)
    public List<Urun> getAll(){
        return urunService.findAll();
    }
}
