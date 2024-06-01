package com.muhammet.satis.config;


public class RestApis {
    private static final String VERSION = "/v1";
    private static final String DEVELOPER = "/dev";
    private static final String TEST = "/test";
    private static final String API = "/api";
    private static final String COMPOUND = DEVELOPER+VERSION;
    public static final String SATIS = COMPOUND+"/satis";
    public static final String MUSTERI = COMPOUND+"/musteri";
    public static final String URUN = COMPOUND+"/urun";

    public static final String SAVE = "/save";
    public static final String FINDALL = "/find-all";
    public static final String GETALL = "/get-all";

    public static final String FINDBYID = "/find-by-id";
    public static final String DELETE = "/delete";


}
