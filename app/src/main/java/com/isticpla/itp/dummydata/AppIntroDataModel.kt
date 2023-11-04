package com.isticpla.itp.dummydata

import com.isticpla.itp.R
import java.sql.Array
import java.sql.Date

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
val listofCarousel = listOf<Int>(R.mipmap.caroussel1, R.mipmap.caroussel2, R.mipmap.caroussel3)

data class BusinessTypeItem(val id: Int, var isSelected: Boolean, val icon: Int, val label: String)

val listofBusiness = listOf<BusinessTypeItem>(
    BusinessTypeItem(1, false, R.drawable.ico_metal, "Metal"),
    BusinessTypeItem(2, true, R.drawable.ico_wood, "Ağaç"),
    BusinessTypeItem(3, true, R.drawable.ico_plastic, "Plastik"),
    BusinessTypeItem(4, true, R.drawable.ico_glass, "Cam"),
    BusinessTypeItem(5, true, R.drawable.ico_textile, "Tekstil"),
    BusinessTypeItem(6, true, R.drawable.ico_others, "Diğer")
)


val listofShops = listOf<Pair<String, String>>(
    Pair<String, String>("Tilman's Hamburger \nNeuenfelderstraße 84, Hamburg", "1"),
    Pair<String, String>("Lu Bu Soul Food\nChristophstraße 7A, 80538 München", "2"),
    Pair<String, String>("Caffe Vinica\nSchönfeldstraße 24, 80539 München", "3")
)

data class HomeDesignItem(val id: Int, val image: Int, val title: String, val price: String)

val listofDesigns = listOf<HomeDesignItem>(
    HomeDesignItem(1, R.mipmap.model_01, "Spor Giyim", "$79.99"),
    HomeDesignItem(2, R.mipmap.model_02, "Punk Giyim", "$74.99"),
    HomeDesignItem(3, R.mipmap.model_03, "Abiye Giyim", "$139.99"),
    HomeDesignItem(4, R.mipmap.model_04, "Vintage Giyim", "$42.99"),
    HomeDesignItem(5, R.mipmap.model_05, "Grunge Giyim", "$17.99"),
)

val listofStokSale = listOf<HomeDesignItem>(
    HomeDesignItem(1, R.mipmap.model_01, "Spor Giyim Spor Giyim Spor Giyim Spor Giyim", "$79.99"),
    HomeDesignItem(2, R.mipmap.model_02, "Punk Giyim", "$74.99"),
    HomeDesignItem(3, R.mipmap.model_03, "Abiye Giyim", "$139.99"),
    HomeDesignItem(4, R.mipmap.model_04, "Vintage Giyim", "$42.99"),
    HomeDesignItem(5, R.mipmap.model_05, "Grunge Giyim", "$17.99"),
)

//imPosition null = genel, 1 sola dayali, 2 saga dayali
data class HomeCampaignItem(
    val id: Int,
    val uitype: Int,
    val image: Int,
    val imPosition: Int?,
    val title: String,
    val subTitle: String
)

val listofHomeCampaigns = listOf<HomeCampaignItem>(
    HomeCampaignItem(
        1,
        1,
        R.mipmap.tmodel_01,
        null,
        "For Slim & Beauty",
        "Sale up to 40%"
    ),
    HomeCampaignItem(
        2,
        1,
        R.mipmap.tmodel_02,
        null,
        "Most Sexy & Fabulous Design",
        "Summer Collection 2023"
    ),
    HomeCampaignItem(3, 1, R.mipmap.tmodel_03, null, "The Office Life", "T-Shirts"),
    HomeCampaignItem(4, 1, R.mipmap.tmodel_04, null, "Elegant Design", "Dresses"),
)

data class SectorNewsItem(val icon: Int, val title: String, val content: String, val date: String)

val listofHomeSectorNews = listOf<SectorNewsItem>(
    SectorNewsItem(
        R.mipmap.model_03,
        "Türkiye - Çin Ticaret Köprüsü Başlıyor",
        "Ticaret Bakanlığından paylaşılan bir açıklama da, Pekin Büyükelçiliği Ticaret Müşavirliği ...",
        "27.04.2023"
    ),
    SectorNewsItem(
        R.mipmap.model_06,
        "China International Supply Chain Expo",
        "Ticaret Bakanlığından alınan bir yazıda, Pekin Büyükelçiliği Ticaret Müşavirliği tarafından...",
        "27.04.2023"
    ),
    SectorNewsItem(
        R.mipmap.model_02,
        "China International Supply Chain Expo",
        "Ticaret Bakanlığından alınan bir yazıda, Pekin Büyükelçiliği Ticaret Müşavirliği tarafından...",
        "26.08.2023"
    ),
)

data class DefaultListAttachmentItem(val icon: Int?, val title: String?)
data class DefaultListItem(
    val date: String,
    val icon: Int?,
    val title: String,
    val subTitle: String?,
    val content: String?,
    val defaultListAttachmentItem: DefaultListAttachmentItem?
)

val listofNotifications = listOf<DefaultListItem>(
    DefaultListItem(
        "4 gün önce",
        null,
        "Teklif talebiniz onaylandı ve teklifler hazırlanmaya başlandı",
        "Berlin fuarında kaydedilenlerden oluşuyor",
        null,
        null
    ),
    DefaultListItem(
        "4 gün önce",
        null,
        "Teklifler geldi!",
        "Berlin fuarında kaydedilenlerden oluşuyor",
        null,
        null
    ),
    DefaultListItem(
        "4 gün önce",
        null,
        "Bir mesajınız var",
        "Berlin fuarında kaydedilenlerden oluşuyor",
        null,
        null
    ),
    DefaultListItem(
        "4 gün önce",
        null,
        "Tedarikçiniz fiyat teklifini gönderdi",
        "Berlin fuarında kaydedilenlerden oluşuyor",
        null,
        null
    ),
    DefaultListItem(
        "4 gün önce",
        null,
        "Siparişiniz yola çıktı",
        "Berlin fuarında kaydedilenlerden oluşuyor",
        null,
        null
    )
)
val listofTasks = listOf<DefaultListItem>(
    DefaultListItem(
        "3 dakika önce",
        null,
        "E-Posta adresini doğrula",
        "E-Posta adresini doğrulamadan teklif talebi oluşturamazsın ve sipariş gönderemezsin.",
        null,
        null
    ),
    DefaultListItem(
        "8 saat önce",
        null,
        "Şirket bilgilerinizi tamamlayınız",
        "Şirketinizin bilgilerini tamamlamadan teklif talebi oluşturamazsın ve sipariş gönderemezsin.",
        null,
        null
    ),
)
val listofMessages = listOf<DefaultListItem>(
    DefaultListItem(
        "4 gün önce",
        null,
        "ITP Ürün Temsilcisi",
        "Ürün cam kırılmazlık testlerini içeren dosya ekte mevcuttur. İncelediğinizde göreceksiniz ki bu testler ürünün sağlamlığını ne olarak ifade etmektedir.",
        null,
        DefaultListAttachmentItem(R.mipmap.hamburger_01, "Indonesian burger table")
    ),
    DefaultListItem(
        "4 gün önce",
        null,
        "ITP Ürün Temsilcisi",
        "Ödemeniz tarafımıza ulaşmıştır",
        null,
        DefaultListAttachmentItem(R.mipmap.hamburger_01, "Indonesian burger table")
    ),
)

data class FeedDashboardItems(
    val id: Int,
    val type: Int, //1 large, 2 medium, 3 no foto
    val date: String,
    val image: Int?,
    val spottext: String?,
    val title: String,
    val content: String?
)

val listofFeedDashboard = listOf<FeedDashboardItems>(
    FeedDashboardItems(
        1,
        1,
        "27.04.2023",
        R.mipmap.fm01,
        "Autumn\nCollection\n2022",
        "China International Supply Chain Expo",
        "Ticaret Bakanlığından alınan bir yazıda, Pekin Büyükelçiliği Ticaret Müşavirliği tarafından..."
    ),
    FeedDashboardItems(
        2,
        1,
        "27.04.2023",
        R.mipmap.fm01,
        "Autumn\nCollection\n2022",
        "China International Supply Chain Expo",
        "Ticaret Bakanlığından alınan bir yazıda, Pekin Büyükelçiliği Ticaret Müşavirliği tarafından..."
    ),
    FeedDashboardItems(
        3,
        2,
        "27.04.2023",
        R.mipmap.fm01,
        null,
        "China International Supply Chain Expo",
        "Ticaret Bakanlığından alınan bir yazıda, Pekin Büyükelçiliği Ticaret Müşavirliği tarafından..."
    ),
    FeedDashboardItems(
        4,
        3,
        "27.04.2023",
        null,
        null,
        "China International Supply Chain Expo",
        "Ticaret Bakanlığından alınan bir yazıda, Pekin Büyükelçiliği Ticaret Müşavirliği tarafından..."
    )
)

data class OfferDraftListItem(
    val id: Int,
    val image: Int?,
    val date: String,
    val title: String,
    val status: Int
)

val listOfOfferDraft = listOf<OfferDraftListItem>(
    OfferDraftListItem(1, R.mipmap.spagetti_01, "27/08/2023", "Lezzetli makarna", 1),
    OfferDraftListItem(2, R.mipmap.pizza_01, "27/08/2023", "Karışık pizza", 1),
    OfferDraftListItem(3, R.mipmap.cocktails, "27/08/2023", "Ev yapımı enfes\nkokteyl", 1),
    OfferDraftListItem(4, R.mipmap.hamburger_01, "27/08/2023", "Karışık pizza", 1)
)

enum class OrderStagesStatus(val result: String) {
    TAMAMLANDI(result = "Tamamlandı"),
    BEKLIYOR(result = "Bekliyor...")
}

data class OrderStages(
    val id: Int,
    val label: String,
    val icon: Int,
    val status: OrderStagesStatus,
    val uri: String
)

val listOfOrderStages = listOf<OrderStages>(
    OrderStages(
        1,
        "Görsel Bilgiler",
        R.drawable.ico_offer_image,
        OrderStagesStatus.TAMAMLANDI,
        "offer/create/visualdetails"
    ),
    OrderStages(
        1,
        "Ürün Detayları",
        R.drawable.ico_offer_product,
        OrderStagesStatus.BEKLIYOR,
        "offer/create/productdetails"
    ),
    OrderStages(
        1,
        "Sipariş Detayları",
        R.drawable.ico_offer_order,
        OrderStagesStatus.BEKLIYOR,
        "offer/create/requestdetails"
    ),
    OrderStages(
        1,
        "Önizleme",
        R.drawable.ico_offer_preview,
        OrderStagesStatus.BEKLIYOR,
        "offer/create/preview"
    )
)
