package com.muhammet.satis.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UrunSaveRequestDto {
    String ad;
    String aciklama;
    Double fiyat;
    Integer kdv;
    Integer stok;
    String marka;
    String model;
    String resimUrl;
    String barkod;
    String tedarikciFirma;
    String tedarikciFirmaIletisim;
    String tedarikciFirmaAdres;
    String tedatikciFirmaYetkili;
    Long stokGirisTarihi;
    Long stokGuncellemeTarihi;
}
