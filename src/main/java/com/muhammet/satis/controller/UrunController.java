package com.muhammet.satis.controller;

import com.muhammet.satis.config.RestApis;
import com.muhammet.satis.dto.request.UrunSaveRequestDto;
import com.muhammet.satis.entity.Satis;
import com.muhammet.satis.entity.Urun;
import com.muhammet.satis.service.UrunService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    /**
     * application.properties ya da application.yml dosyasında var olan
     * my-application.depo ya da my-application:
     *                                  depo:
     * şeklindeki anahtarların değer bilgisi üzerinde bulunulan değişkene atanır.
     */
    @Value("${my-application.depo}")
    private String depoAdi;
    @Value("${my-application.depo2}")
    private String depo2;
    @Value("${my-application.kullanici-bilgileri.user-name}")
    private String userName;
    @Value("${my-application.kullanici-bilgileri.password}")
    private String password;
    private final UrunService urunService;
    @PostMapping(SAVE)
    public void save(UrunSaveRequestDto dto){
        urunService.save(dto);
    }

    @GetMapping(GETALL)
    public List<Urun> getAll(){
        return urunService.findAll();
    }

    @GetMapping(DEPO)
    public String getDepoAdres(){
        return depoAdi;
    }

}
