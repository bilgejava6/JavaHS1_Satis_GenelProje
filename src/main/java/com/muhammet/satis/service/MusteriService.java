package com.muhammet.satis.service;

import com.muhammet.satis.entity.Musteri;
import com.muhammet.satis.repository.MusteriRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DİKKAT!!!!!!
 * Servis katmanları bizim iş katmanlarımızdır ve Spring Bu sınıflarıdan bir referans
 * oluşturur ve bunu Application Context inin içerisine ekler. Bunu yapabilmesi için
 * SpringComponentScan in bu sınıfı bulması gereklidir. Bunu bulabilmesi için
 * @Service anotasyonu ile işaretlenmesi gereklidir.
 */
@Service
public class MusteriService {

    /**
     * DİKKAT!!!!!!
     * Her servis kendi iletişimdeki katmanı ile işlmelerini yapar, yani;
     * MusteriServis -> MusteriRepository yi kullanır. Başka bir Repository
     * sınıfını içeriye almaz ya da yönetemez.
     * PEKİ YA DİĞER VERİLERE İHTİYACIM OLURSA
     * Bir servis diğer bir servis ile iletişime geçer, yani başka bir repository ile işiniz var ise
     * bunu ilgili repositorynini servisi ile çözeceksiniz.
     *
     */
    private final MusteriRepository musteriRepository;

    /**
     * Contructor Injection
     * @param musteriRepository
     */
    public MusteriService(MusteriRepository musteriRepository){
        this.musteriRepository = musteriRepository;
    }
    public void save(Musteri musteri){
        musteriRepository.save(musteri);
    }

    public List<Musteri> getAll(){
        return musteriRepository.findAll();
    }

    public List<Musteri> getAll(String ad){
        return musteriRepository.findAllByAd(ad);
    }

}
