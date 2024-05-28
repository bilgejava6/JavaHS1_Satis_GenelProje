package com.muhammet.satis.repository;

import com.muhammet.satis.entity.Musteri;
import com.muhammet.satis.entity.view.VwMusteriArama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Interface, sözleşmemiz, bir varlığı soyutlama işlemlerinde kullanılır.
 * new ile yeni nesne oluşturulamaz.
 * Interface -> MusteriRepository
 * Class -> MusteriRepositoryImpl, spring gerekli implementasyon sınıfından bir nesneyi kendi yaratır.
 * -------
 * Dikkat!!!!
 * Spring 3.X ten önce Repository sınıflarının üzerinde @Repository anotasyonu eklemek zorunlu idi.
 * Spring >3+ ile birlikte bu zorunluluk kalktı çünkü Repository interface leri miras alındığı için
 * spring bunu algılıyor ve sınıf için bir Bean yaratıyor.
 */
public interface MusteriRepository extends JpaRepository<Musteri,Long> {

    /**
     * Spring Data Query
     * Spring Interface lerimizde tanımını yaptığımız tüm moethodlar için impl oluşturur. Ancak
     * burada dikkat etmeniz gereken kısımlar vardır. Belli bir düzende method adlarını ve parametrelerini
     * oluşturmak zorundasınız.
     * Örnek Sorgu: List<Musteri> findAllByAdAndSoyad(String ad, String soyad);
     * 1-> ReturnType: sorgu ile hedeflenen sonucu giriniz; Musteri,Optional<Musteri>, List<Musteri>, Boolean, Integer
     * 2-> "find, exist, count" gibi kelimeler ile başlamalıdır.
     * 3-> başlangıç kelimesinden sonra özel bir belirteç yok ise "by" ile devam ederiz.
     * 3.1-> eğer bir sonuc kısıtlanacak ise ya da dönüş türüne müdehale edilecek ise araya ek keywordler
     * girebilir. "Top, Optional" gibi
     * 4-> İlgili repository nin kullandığı Entity sınıfı içinde yer alan değişken adlarını ilave edeceğiz, Ancak
     * burada önemli olan konu şudur, "by" ifadesinden sonra Spring in method ismini analiz edebilmesin için kelimelerin
     * Büyük harfle ayrıştırılması gereklidir. Öneğin;
     * Sınıf değişkeni "dogumTarini" olsun bunu bir sorguya eklerken
     * findAllByDogumTarihi şeklinde eklemek gereklidir, yani dogumTarihi -> DogumTarihi şeklinde yazılır
     * 4.1-> Eğer birden fazla entity değişkeni bir birine eklenerek kullanılaak ise "And, Or" şeklinde
     * eklenerek devam edilir.
     * 4.2-> Bazen Equals değilde belli kritertlerde arama yapmak isteyebiliriz, "Like, ILike, GratherThan vs." bu
     * tarz bileşenler Entity değişkenlerini yanına eklenerek sorgu kuvvetlendirilir. ÖRnek;
     * "findAllByAdLikeAndSoyad"
     * 5-> Merhodun parametre parantezi içerisine ihtiyaç duydunuz değişkenleri yazarız. Burada önemli olan bir
     * kaç husus vardır;
     * - Talep ettiğiniz entity değişkenini türü ne ise buna uygun parametre talep edersiniz. Yani adına göre
     * arama yazdığınız bir method için String bir değilen talep edilir.
     * - Talep edilen değişkenlerin sıralaması önemlidir, yani method a hangi sıra ile ekleme yapıldı ise
     * o sıra ile değer talep edilir.
     * DİKKAT!!!! Method için talep edilen değişken adlarının Entity ya da sorgu ile alakası yoktur,
     * bu nedenle lütfen değişken adlarını method içi ile karştırmayın.
     * findAllByAdAndSoyad(String ad, String soyad); böyle bir method ta select sorgusu şöyle çalışır
     * select * from tblmusteri where ad= ?ad and soyad= ?soyad şeklinde çalışır burada değişken adının önemi yoktur.
     * Yani şöyle bir sorgunun;
     * findAllByAdAndSoyad(String soyad, String ad);
     * select * from tblmusteri where ad= ?soyad and soyad= ?ad
     * dikkat ederseniz değişkenin yer değiştirmesinin bir anlamı yoktur. sıra ile sorguya eklenirler.
     * BU NEDENLE DEĞİŞKEN ADININ BU KISIMDA ÖNEMİ YOKTUR; ÖRNEK,
     * findAllByAdAndSoyad(String ahmetAmcaNasilsin, String iyiyimYegen);
     * select * from tblmusteri where ad= ?ahmetAmcaNasilsin and soyad = ?iyiyimYegen
     *
     */

    /**
     * select * from tblmusteri where ad= ?aliVeliNasilsin
     */
    List<Musteri> findAllByAd(String aliVeliNasilsin);

    List<Musteri> findAllBySoyadAndAdres(String bisey, String baskaBisey);

    /**
     * select * from tblmusteri where yas > ?
     * > -> Greater
     * < -> Less
     * >= -> Greater + Equal = GreaterThanEqual
     * <= ->
     */
    List<Musteri> findAllByYasGreaterThan(Integer yas);
    List<Musteri> findAllByYasLessThan(Integer yas);
    List<Musteri> findAllByYasGreaterThanEqual(Integer yas);
    List<Musteri> findAllByYasLessThanEqual(Integer yas);

    /**
     * % ve _ ile özel sorgular yazabiliyoruz.
     * select * from tblmusteri ad  like ?
     * DİKKAT!!!
     * ad = ahmet olabilir
     * ad = mht olabilir
     * ad = %mh% olabilir
     * ad = __a%__t olabilir
     */
    List<Musteri> findAllByAdLike(String ad);

    /**
     * select * from tblmusteri ad like ?% -> ah%
     */
    List<Musteri> findAllByAdStartingWith(String ad);

    /**
     * birden fazla alanın bir biri ile iliştirilmesi
     * And, Or kullanımı
     * select * from tblmusteri ad like ? and soyad like ? or yas > ?
     */
    List<Musteri> findAllByAdLikeAndSoyadLikeOrYasGreaterThan(String ad,String soyad,Integer yas);

    /**
     * Sonuçların Optional olarak döndürülmesi
     *  select * from tblmusteri where userName = ?
     *  DİKKAT!!!
     *  Eğer değişken adların birden çok kelimeden oluşuyor ise,
     *  userName, postCode, userNameSurname gibi bu tarz değişenlerin
     *  isimlendirilmesinde sadece baş harfleri büyük yazılır diğer kelimeler yazıldığı gibi bırakılır
     *  findByUserName, findByPostCode, findByUserNameSurname
     *  ÖNEMLİ, eğer birden fazla kelime içeren değişken tamamen küçük yazılmış ise,
     *  username, postcode, usernamesurname, yine sadece Baş harfleri büyük diğer kısımlar yazıldığı gibi
     *  findByUsername, findByUsernamesurname, findByPostcode
     *  !!!! ÇOOK ÖNEMLİ !!!!!!
     *  Eğer bir sorgu birden fazla değer dönebilecek ise asla Optional yazamazsınız çünkü
     *  birden fazla değer geldiğinde sorgu excepiton fırlatır.
     */
    Optional<Musteri> findOptionalByUserName(String userName);
    List<Musteri> findAllByUsernameandsurname(String userNameAndSurname);


    /**
     * Order
     * select * from tblmusteri order by ad desc  z...a, 9...0
     */
    List<Musteri> findAllByOrderByAd(); // asc
    List<Musteri> findAllByOrderByAdDesc();
    List<Musteri> findAllByYasGreaterThanOrderByYasDesc(Integer yas);


    /**
     * Sonuçları kısıtlama -> LIMIT, TOP
     *
     * Ali    43
     * Ahmet  7
     * Canan  99
     * Hakkı  49
     * Batu  12
     *
     */
    List<Musteri> findAllByOrderByYas(); // Tüm kullanıcıları yaşına göresıralayarak getirir.
    List<Musteri> findTop5ByOrderByYas(); // en genç 5 müşteriyi getirir.
    // En yaşlı müşteriyi getir. Ya tablo boş ise?
    /**
     * select limit 1 from tblmusteri order by yas desc
     */
    Optional<Musteri> findTopOptionalByOrderByYasDesc();
    Optional<Musteri> findTopOptionalByAdLikeOrderByYasDesc(String ad);

    /**
     * select * from tblmusteri where yas>=5 and yas<=26 [5,26]
     */
    List<Musteri> findAllByYasBetween(Integer min, Integer max);
    List<Musteri> findAllByAdLikeAndYasBetween(String ad, Integer bas, Integer bit);

    /**
     * true-false
     * select * from tblmusteri where isActive = ?
     */
    List<Musteri> findAllByIsActive(Boolean isActive);
    List<Musteri> findAllByIsActiveTrue();
    List<Musteri> findAllByIsActiveFalse();

    /**
     * Bir aland arama yaparken bazen caseSensitve bizi zorlayabilir. bu nedenle aramaları
     * duyarlı hale getirebiliriz.
     */
    List<Musteri> findAllByAdIgnoreCaseAndSoyadIgnoreCase(String ad,String soyad);


    /**
     * Arama yapaken bir den fazla değer için arama yapma işlemi
     * select * from tblmusteri where ad in('Murat','Demet','Bahar')
     */
    List<Musteri> findAllByAdIn(List<String> adList);

    /**
     * aynı olan kayıtları tekilleşirme
     */

    List<Musteri> findDistinctByAd(String ad);

    /**
     * TAblo içinde değerleri null olan alanları bulmak için
     * NULL, ISNULL, NOTNULL, ISNOTNULL
     */

    List<Musteri> findAllByAdresIsNull(); // adres alanı null olanları listele
    List<Musteri> findAllByAdresIsNotNull(); // adres alanı null olmayanları listele

    /**
     * Aranılan değer bir metin içerisinde geçip geçmediğini bulmak
     * select * from tblmusteri where ad like %?%
     */
    List<Musteri> findAllByAdContaining(String ad);

    /**
     * Select * from tblmusteri where ad like %?
     */
    List<Musteri> findAllByAdEndingWith(String ad);

    /**
     * Queries
     * DİKKAT!!!!!!
     * Spring bir method için query yazdığınızda method adı ile işi biter, yani özel keyword ler
     * kullanmak zorunda değilsiniz. Spring ilgili methodun işlevini anahtar kelimeler yerine
     * query içerisine yazığınız SQL cümlesi üzerinden yürütür.
     * HQL, JPQL, NATIVESQL
     */

    /**
     *
     * JPQL Kullanım örnekleri
     */
    @Query("select m from Musteri m")
    List<Musteri> tumMusterileriGetir();

    @Query("select m from Musteri m where m.ad= ?1")
    List<Musteri> adinaGoreHepsiniGetir(String ad);

    @Query("select m from Musteri m where m.ad= ?3 and m.soyad= ?1 and m.adres=?2 ")
    List<Musteri> adVeSoyadVeAdresineGoreGetir(String soyad,String adres,String ad);

    /**
     * NativeSQL Kullanım örneği
     *
     */
    @Query(value = "select * from Musteri where telefon= ?1", nativeQuery = true)
    List<Musteri> findAllByTelefon(String telefon);

    /**
     * Sorrgularda Parametre kullanımı
     */
    @Query("select m from Musteri m where m.userName like :userName and m.yas> :yasinDegeri")
    List<Musteri> userNameIleAra(
            @Param("userName") String userName,
            @Param("yasinDegeri") Integer musteriYasi);

    /**
     * Return type ı Entity dışında olan sorgular, Boolen, Integer, Long v.s.
     */
    @Query("select COUNT (m)>0 from Musteri m where m.userName= ?1 and m.password= ?2")
    Boolean kullaniciyiDogrula(String userName, String password);

    /**
     * View kullanımını özel sınıflar kullanarak aktif edebiliriz. Burada amaç performans artışı ve
     * gereksiz sorguların önüne geçmek.
     */
    @Query("select new com.muhammet.satis.entity.view.VwMusteriArama(m.id,m.ad,m.userName) from Musteri m where m.userName like ?1")
    List<VwMusteriArama> findAllByUserNameLike(String userName);




}




