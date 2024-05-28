package com.muhammet.satis.controller;

import com.muhammet.satis.dto.request.MusteriSaveRequestDto;
import com.muhammet.satis.entity.Musteri;
import com.muhammet.satis.repository.MusteriRepository;
import com.muhammet.satis.service.MusteriService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
/**
 * @Controller -> Genellikle Spring MVC için kullanırız.
 * @RestController -> RespAPI için oluşturulacak yapılarda kullanılır.
 * Http Request
 * --------------
 * -> Bir URL adresi barındırır. (http(s)://IP:PORT/[end-point])
 * -> Request Type (GET,POST,PUT,DELETE,PATCH)
 * -> GET = genellikle bilgi almak için kullanılan istektir. Internet sayfalarını açma isteği bu türden bir istektir.
 *          eğer sunucuya bilgi iletilecek ise Request Header içerisinde istekler iletilir. Örn;
 *          /musteri/findById?id=5 (burada ? değer iletileceğini, id değerin adını ve 5 değeri ifade eder.)
 * -> POST = post isteği sunucuya kayıt işlemleri için atılan istektir. İstek içerisinde iletilecek veriler
 *           request body içerisinde iletişir. Güvenli iletişime sahiptir.
 *
 * ÖDEV!!!!!
 * - Http Request Type listesini ve bunları ne iş yaptıklarını öğreniyorsunuz.
 * - Http Response Code listesini ve code ların en bilindiklerinin neler olduğunu ne işe yaradığını öğreniyorsunuz.
 *
 *
 * Uygulamanın kök URl adesinden sonra gelecek olan isteklei handle edecek end-point ifadesidir.
 * Bu ifade URL yi filtreleyerek ilgili sınıfa yönlenmesini sağlar. Yani;
 * http://localhost:9090/musteri şeklinde gelen bir isteğin yakalanarak bu sınıfa yönlenmesi sağlanır.
 */
@RequestMapping("/musteri")
@RequiredArgsConstructor
public class MusteriController {
    private final MusteriService musteriService;
    /**
     * Kaydetme işlemi -> POST ile kullanılmalıdır
     * Bilgileri Sorgulama getirme -> GET ile kullanılmalıdır.
     *
     */

    /**
     * Kullanıcıdan POST işlemi ile veri alacak iseniz, datalar body üzerinden gelir bu nedenle
     * verileri DTO kullanarak almak en doğru yöntemdir. Method parametresi olarak almak doğru
     * değildir. İlerleyen zamanlarda başka datalar da talep edilme durumu olabileceğinden böyle
     * yapınız.
     *
     * POST: http://localhost:9090/musteri/save{
     *     body: {
     *         ad: "muhammet",
     *         soyad:"HOCA",
     *         userName: "muhammet",
     *         password: "123456"
     *     }
     * }
     *
     */
    @PostMapping("/save")
    @CrossOrigin("*")
    public void save(@RequestBody MusteriSaveRequestDto dto){
        Musteri musteri = Musteri.builder()
                .ad(dto.getAd())
                .soyad(dto.getSoyad())
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .build();
        musteriService.save(musteri);
    }
    // GET: http://localhost:9090/musteri/get-all
    @GetMapping("/get-all")
    public ResponseEntity<List<Musteri>> getAll(){
        return ResponseEntity.ok(musteriService.getAll());
    }



}
