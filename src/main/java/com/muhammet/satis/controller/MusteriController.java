package com.muhammet.satis.controller;

import com.muhammet.satis.entity.Musteri;
import com.muhammet.satis.repository.MusteriRepository;
import com.muhammet.satis.service.MusteriService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
/**
 * Uygulamanın kök URl adesinden sonra gelecek olan isteklei handle edecek end-point ifadesidir.
 * Bu ifade URL yi filtreleyerek ilgili sınıfa yönlenmesini sağlar. Yani;
 * http://localhost:9090/musteri şeklinde gelen bir isteğin yakalanarak bu sınıfa yönlenmesi sağlanır.
 */
@RequestMapping("/musteri")
@RequiredArgsConstructor
public class MusteriController {
    private final MusteriService musteriService;
    /**
     * End-Point: http://localhost:9090/musteri/kaydet?ad=muhammet
     * @param ad
     * @param soyad
     * @param telefon
     * @return
     */
    @GetMapping("/kaydet")
    public ResponseEntity<String> save(String ad, String soyad, String telefon){
        if(Objects.isNull(ad))
            return ResponseEntity.badRequest().build();
        musteriService.save(Musteri.builder()
                        .ad(ad)
                .build());
        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Musteri>> getAll(String ad){
        if(Objects.isNull(ad))
            return ResponseEntity.ok(musteriService.getAll());
        return ResponseEntity.ok(musteriService.getAll(ad));
    }

}
