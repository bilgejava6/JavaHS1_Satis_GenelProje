package com.muhammet.satis.service;

import com.muhammet.satis.dto.request.UrunSaveRequestDto;
import com.muhammet.satis.entity.Satis;
import com.muhammet.satis.entity.Urun;
import com.muhammet.satis.mapper.UrunMapper;
import com.muhammet.satis.repository.UrunRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UrunService {

    private final UrunRepository urunRepository;


    public void save(UrunSaveRequestDto dto) {
//        urunRepository.save(Urun.builder()
//                        .ad(dto.getAd())
//                        .aciklama(dto.getAciklama())
//                        .barkod(dto.getBarkod())
//                        .fiyat(dto.getFiyat())
//                        .kdv(dto.getKdv())
//                        .marka(dto.getMarka())
//                        .model(dto.getModel())
//                        .resimUrl(dto.getResimUrl())
//                        .stok(dto.getStok())
//                        .stokGirisTarihi(dto.getStokGirisTarihi())
//                        .stokGuncellemeTarihi(dto.getStokGuncellemeTarihi())
//                        .tedarikciFirma(dto.getTedarikciFirma())
//                        .tedarikciFirmaAdres(dto.getTedarikciFirmaAdres())
//                        .tedarikciFirmaIletisim(dto.getTedarikciFirmaIletisim())
//                        .tedatikciFirmaYetkili(dto.getTedatikciFirmaYetkili())
//                .build());
       // Urun urun = UrunMapper.INSTANCE.ahmetAmcaNasilsin(dto);
        urunRepository.save(UrunMapper.INSTANCE.ahmetAmcaNasilsin(dto));
    }

    public List<Urun> findAll() {
       return urunRepository.findAll();
    }
}
