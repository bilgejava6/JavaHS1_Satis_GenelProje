package com.muhammet.satis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tblurun")
public class Urun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
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
    Long createAt;
    Long updateAt;
    Integer state;
}
