package com.lol.jona.loltest;

/**
 * Created by Jona on 11-10-2015.
 */
public class Constants {
    //INFORMACION A OBTENER
    public static String CHAMP_DATA="?champData=all";

    //LLAVE PARA LA API
    public static String API_KEY="&api_key=your_api_key";

    //IMAGEN DEL CAMPEON(icono)
    public static String IMG_URL="http://ddragon.leagueoflegends.com/cdn/5.19.1/img/champion/";
    //SPLASH ART DEL CAMPEON
    public static String CHAMP_SPLASH="http://ddragon.leagueoflegends.com/cdn/img/champion/splash/";
    //OBTENER INFE SOBRE 1 CAMPEON POR ID
    public static String API_CHAMP="https://global.api.pvp.net/api/lol/static-data/las/v1.2/champion/";
    //OBTENER INFO DE TODOS LOS CAMPEONES
    public static String API_ALL_CHAMPS="https://global.api.pvp.net/api/lol/static-data/las/v1.2/champion"+CHAMP_DATA+API_KEY;
    //OBTENER IMAGEN DE LA PANTALLA DE CARGA
    public static String LOAD_IMG="http://ddragon.leagueoflegends.com/cdn/img/champion/loading/";//Aatrox_0.jpg;
    //SPELLS IMAGE
    public static String SKILL_IMG="http://ddragon.leagueoflegends.com/cdn/5.20.1/img/spell/";//FlashFrost.png
    //PASSIVE SPELL
    public static String PASIV_SPELL="http://ddragon.leagueoflegends.com/cdn/5.20.1/img/passive/";
    //public static String API_CHAMP="http://ddragon.leagueoflegends.com/cdn/img/champion/splash/";

    //FREE TO PLAY CHAMPS
    public static String FREE_TO_PLAY="https://las.api.pvp.net/api/lol/las/v1.2/champion?freeToPlay=true"+API_KEY;

}
