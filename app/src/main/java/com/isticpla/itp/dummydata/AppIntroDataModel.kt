package com.isticpla.itp.dummydata

import com.isticpla.itp.R
import java.sql.Array

data class AppIntroDataModel(val img: Int, val title: String, val content: String)

fun AppIntroData(): ArrayList<AppIntroDataModel> {
    var listofAppIntroData = arrayListOf<AppIntroDataModel>()
    listofAppIntroData.add(
        AppIntroDataModel(
            R.drawable.symb_derinurunanalizi,
            "Derin Ürün Analizi",
            "Ürünlerin kapsamlı bir şekilde incelenmesini sağlayan bir analiz hizmetidir. Bu analiz, ürünün özellikleri, rekabet avantajları, müşteri talepleri ve pazar potansiyeli gibi faktörleri ayrıntılı olarak değerlendirir."
        )
    )
    listofAppIntroData.add(
        AppIntroDataModel(
            R.drawable.symb_coklutaslakyontemi,
            "Çoklu Taslak Yönetimi",
            "Ürün araştırma sürecinde birden fazla taslağı etkili bir şekilde yönetme imkanı sunar ve araştırmalarınızı silmek zorunda kalmazsınız."
        )
    )
    listofAppIntroData.add(
        AppIntroDataModel(
            R.drawable.symb_urunteklifvetedarik,
            "Ürün Teklif ve Tedarik",
            "Tedarik ihtiyaçlarınızın üreticiler arasında araştırılması, size fiyat teklifleri sunulması ve ardından üretilip mağazanıza teslim edilmesi süreçlerini içerir."
        )
    )
    listofAppIntroData.add(
        AppIntroDataModel(
            R.drawable.symb_coklumagazayonetimi,
            "Çoklu Mağaza Yönetimi",
            "Sahip olduğunuz diğer mağazalarınız için ürün tedariklerini ayrı ayrı yönetme imkanı sunar. Mağaza değişikliği yaparak, uygulama içeriğini mağazanıza özgün hale getirebilirsiniz."
        )
    )
    listofAppIntroData.add(
        AppIntroDataModel(
            R.drawable.symb_ekhizmetler,
            "Ek Hizmetler",
            "Web tasarım, SEO, sosyal medya yönetimi, içerik üretimi, dijital reklam, e-posta pazarlama, analiz ve raporlama gibi çeşitli dijital pazarlama hizmetleri sunar."
        )
    )
    return listofAppIntroData
}

val listofAraeCodes =
    listOf<Pair<String, String>>(
        Pair("TR", "+ 90"),
        Pair("US", "+1"),
        Pair("GB", "+44"),
        Pair("DE", "+49"),
        Pair("GR", "+30")
    )
val listofEmployeePosition =
    listOf<Pair<String, String>>(
        Pair("CEO", "ceo"),
        Pair("Position 1", "position1"),
        Pair("Position 2", "position2"),
        Pair("Position 3", "position3"),
        Pair("Position 4", "position4")
    )

data class BusinessTypeItem(val id: Int, val icon: Int, val label: String)

val listofBusiness = listOf<BusinessTypeItem>(
    BusinessTypeItem(1, R.drawable.ico_metal, "Metal"),
    BusinessTypeItem(2, R.drawable.ico_wood, "Ağaç"),
    BusinessTypeItem(3, R.drawable.ico_plastic, "Plastik"),
    BusinessTypeItem(4, R.drawable.ico_glass, "Cam"),
    BusinessTypeItem(5, R.drawable.ico_textile, "Tekstil"),
    BusinessTypeItem(6, R.drawable.ico_others, "Diğer")
)

data class ShopDetails(val id: Int, val label: String, val address: String)

val listofShopDetails = listOf<ShopDetails>(
    ShopDetails(1, "Tilman's Hamburger", "Neuenfelderstraße 84, Hamburg"),
    ShopDetails(2, "Lu Bu Soul Food", "Christophstraße 7A, 80538 München"),
    ShopDetails(3, "Caffe Vinica", "Schönfeldstraße 24, 80539 München")
)
val listofShops = listOf<Pair<String, String>>(
    Pair("1", "Tilman's Hamburger"),
    Pair("2", "Lu Bu Soul Food"),
    Pair("3", "Caffe Vinica")
)
