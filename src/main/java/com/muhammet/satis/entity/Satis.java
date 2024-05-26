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
@Table(name = "tblsatis")
public class Satis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long urunId;
    Long musteriId;
    Integer adet;
    Double birimFiyat;
    Double toplamFiyat;
    Long satisTarihi;
}
