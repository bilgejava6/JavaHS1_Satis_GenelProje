package com.muhammet.satis.mapper;

import com.muhammet.satis.dto.request.UrunSaveRequestDto;
import com.muhammet.satis.entity.Urun;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Burası urun sınıfının dto ile dönüşümünü koordine edecek.
 * Burada methodlar için sadece tanım yapılır ve önemli olan iki konu vardır;
 * 1-> Source(kaynak veri - methoda girilen nesde değişkeni)
 * 2-> Target(hedef - yani methodun return type i)
 * DİKKAT!!! Methodun adının bir önemi yoktur. Sadece amaca yönelik yazılırsa
 * okunurluğu artar.
 *
 * Bir Mapper sınıfının algılanabilmesi ve gerekli impl sınıflarının yazıkabilmesi
 * için İlgili sınıfın @Mapper anotasyonu ile işaretlenmesi gereklidir.
 * DİKKAT!!!! Eğer iki alan bir biri ile eşleştirilirken eşleşmeeyen
 * yani eksik alanlar var ise uygulama hata fırlatır. Ancak bizim DTO olarımız
 * genellikle Entity ler ile eşleşmez bu nedenle burada eşleşmeyen alanları
 * GÖRMEZDEN GEL demek zorundayız. İşte bu işlemi
 * @unMappedTargetPolicy ile işaretlemeliyiz.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UrunMapper {

    /**
     * Bu bir interface olduğu için bu sınıfdan bir nesne yaratma işlemi Mappers
     * ile sağlanır. Bu nedenle bu interface içerisine gerekli INSTANCE sabit
     * olarak tanımlanır ve Spring içerisinde direkt çağırılır.
     */
    UrunMapper INSTANCE = Mappers.getMapper(UrunMapper.class);

    /**
     * Resource -> Target
     * UrunDto  -> Urun    e dönüşüm yapmak
     */
    Urun dtoDanUruneDonustururMusun(UrunSaveRequestDto dto);

    Urun ahmetAmcaNasilsin(UrunSaveRequestDto gelBakalimYegen);
}
