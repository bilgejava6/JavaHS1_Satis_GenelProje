package com.muhammet.satis.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MusteriSaveRequestDto {
    @NotNull(message = "Ad alanı boş geçilemez")
    @Size(min = 3,max = 64)
    String ad;
    @NotNull(message = "Soyad alanı boş geçilemez")
    @Size(min = 2,max = 64)
    String soyad;
    @NotNull(message = "kullanıcı adı boş geçilemez")
    @Size(min = 3, max = 64)
    String userName;
    @NotNull(message = "şifre boş geçilemez")
    @Size(min = 8,max = 32)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,32}$")
    String password;
}
