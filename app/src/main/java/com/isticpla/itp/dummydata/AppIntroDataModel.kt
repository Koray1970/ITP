package com.isticpla.itp.dummydata

import androidx.annotation.OpenForTesting
import com.isticpla.itp.R
import java.sql.Array
import java.sql.Date

data class AppCultureDataModel(val id:Int, val icon:Int, val name: String)
val listOfAppCulture= listOf<AppCultureDataModel>(
    AppCultureDataModel(1,R.drawable.flg_tr,"Türkçe"),
    AppCultureDataModel(1,R.drawable.flg_uk,"English"),
    AppCultureDataModel(1,R.drawable.flg_fr,"Français"),
    AppCultureDataModel(1,R.drawable.flg_de,"Deutsch")
)
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
        Pair("1","CEO"),
        Pair("2", "position1"),
        Pair("3", "Position2"),
        Pair("4", "Position3"),
        Pair("5", "position4")
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


val listofShops = listOf<Pair<Int, String>>(
    Pair<Int, String>(1, "Tilman's Hamburger \nNeuenfelderstraße 84, Hamburg"),
    Pair<Int, String>(2, "Lu Bu Soul Food\nChristophstraße 7A, 80538 München"),
    Pair<Int, String>(3, "Caffe Vinica\nSchönfeldstraße 24, 80539 München")
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

enum class OrderStagesStatus(val result: String,val id:Int) {
    BEKLIYOR(result = "Bekliyor...",id=1),
    ODEMEASAMASINDA(result="Ödeme aşamasında",id=1),
    URETIMDE(result="Üretimde", id=2),
    TAMAMLANDI(result = "Tamamlandı",id=2),
    YOLDA(result="Yolda",id=3),
    TESLIMEDILDI(result="Teslim edildi",id=4)
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

enum class FormItemTypes(val type: Int) {
    TEXTFIELD(type = 1), MULTILINETEXTFIELD(type = 2), DROPDOWNMENU_WITH_ADD_BUTTON(type = 3)
}

data class ProductFeatureItem(val id: Int, val label: String, val formItemType: FormItemTypes)

val listofProductFeature = listOf<ProductFeatureItem>(
    ProductFeatureItem(1, "Açıklama", FormItemTypes.MULTILINETEXTFIELD),
    ProductFeatureItem(2, "Örnek Ürün Linki", FormItemTypes.TEXTFIELD),
    ProductFeatureItem(3, "Hammadde", FormItemTypes.TEXTFIELD),
    ProductFeatureItem(4, "Renk", FormItemTypes.DROPDOWNMENU_WITH_ADD_BUTTON),
    ProductFeatureItem(5, "Genişlik", FormItemTypes.TEXTFIELD),
    ProductFeatureItem(6, "Uzunluk", FormItemTypes.TEXTFIELD),
    ProductFeatureItem(7, "Yükseklik", FormItemTypes.TEXTFIELD),
    ProductFeatureItem(8, "Derinlik", FormItemTypes.TEXTFIELD),
    ProductFeatureItem(9, "Setifika", FormItemTypes.TEXTFIELD),
)
val listOfQuantity = listOf<Pair<Int, Int>>(
    Pair(1, 1),
    Pair(2, 10),
    Pair(3, 50),
    Pair(4, 100),
    Pair(5, 200),
    Pair(6, 300),
    Pair(7, 500),
    Pair(8, 10000),
)
val listOfPaymentType = listOf<Pair<Int, String>>(
    Pair(1, "Havale, Standart Ödeme"),
    Pair(2, "Kredi Kartı"),
    Pair(3, "Kapıda Ödeme"),
)
val listOfDeliveryType = listOf<Pair<Int, String>>(
    Pair(1, "Kapıda Teslim"),
    Pair(2, "Kargo Firma Şubesi"),
    Pair(3, "Gel Al"),
)

data class OfferDetailTabItem(
    val label: String, val viewuri: String, val badgeval: Int? = null
)

val listofOfferDetailTabs = listOf<OfferDetailTabItem>(
    OfferDetailTabItem("Genel", "", null),
    OfferDetailTabItem("Detaylar", "", null),
    OfferDetailTabItem("Teklifler", "", 1),
    OfferDetailTabItem("Analiz", "", 1),
    OfferDetailTabItem("Mesajlar", "", 3),
)

data class OfferDetailTrackingItem(
    val isCompleted: Boolean = false,
    val title: String,
    val date: String,
)

val listofOfferDetailTrackings = listOf<OfferDetailTrackingItem>(
    OfferDetailTrackingItem(true, "Parcel is successfully delivered", "15 May 10:20"),
    OfferDetailTrackingItem(true, "Parcel is out for delivery", "14 May 08:00"),
    OfferDetailTrackingItem(true, "Parcel is received at delivery Branch", "13 May 07:25"),
    OfferDetailTrackingItem(true, "Parcel is in transit", "13 May 07:20"),
    OfferDetailTrackingItem(true, "Sender has shipped your parcel", "12 May 14:25"),
    OfferDetailTrackingItem(true, "Sender is preparing to ship your order", "12 May 10:01"),
)

enum class TypeOfPerson(value: Int) {
    ADMIN(1), CLIENT(2)
}

data class OfferChatItem(
    val typeofperson: TypeOfPerson,
    val personalname: String?,
    val icon: Int?,
    val date: String,
    val message: String = ""
)

val listofChatMessages = listOf<OfferChatItem>(
    OfferChatItem(
        TypeOfPerson.ADMIN,
        "ITP Team",
        null,
        "Cumartesi, 12:30",
        "Min Adet: 1,000 Üretim süresi: 21 gün"
    ),
    OfferChatItem(TypeOfPerson.CLIENT, null, R.mipmap.profilephoto, "Pazar, 12:32", "Okay wonder"),
    OfferChatItem(
        TypeOfPerson.ADMIN, "ITP Team", null, "Pazartesi, 09:27", "Kalite: A sınıfı\n" +
                "Kargo: Alıcı ödemeli\n" +
                "Numune: Alıcı ödemeli / 4 gün"
    ),
    OfferChatItem(TypeOfPerson.CLIENT, null, R.mipmap.profilephoto, "Pazar, 12:32", "Okay wonder"),
)

data class ProfileMenuItem(val icon: Int, val label: String, val navuri: String)

val listofProfileMenu = listOf<ProfileMenuItem>(
    ProfileMenuItem(R.drawable.ico_edit, "Profil Düzenle", "profile/edit"),
    ProfileMenuItem(R.drawable.ico_settings, "Ayarlar", "profile/settings"),
    ProfileMenuItem(R.drawable.ico_mystores, "Mağazalarım", "profile/mystores"),
    ProfileMenuItem(
        R.drawable.ico_contractedsuppliers,
        "Anlaşmalı Tedarikçiler",
        "profile/contractedSuppliers"
    ),
    ProfileMenuItem(
        R.drawable.ico_collectionandtags,
        "Kolleksiyonlarım & Etiketlerim",
        "profile/collectionsandtags"
    ),
    ProfileMenuItem(R.drawable.ico_helpandsupport, "Yardım ve Destek", "profile/helpandsupport"),
)

data class MyStoreItem(
    val id: Int,
    val image: Int,
    val title: String,
    val content: String,
    val address: String,
    val webaddress: String,
    val email: String,
    val taglist: List<String> = emptyList<String>(),
    val isactive: Boolean = true,
    val ishide: Boolean = false
)

val listofMyStore = listOf<MyStoreItem>(
    MyStoreItem(
        id = 1,
        image = R.mipmap.profilephoto,
        title = "Indonesian chicken burger company",
        address = "Bostancı mah. nur sokak, no 44, daire 3, Kadıköy, İstanbul, Türkiye",
        content = "<b>6856897584</b> Vergi Numarası\n<b>Brandenburg</b> Vergi Dairesi",
        webaddress = "www.itpturkey.com",
        email = "info@itpturkey.com",
        taglist = listOf<String>("Ahşap", "Cam", "Plastik"),
    ),
    MyStoreItem(
        id = 2,
        image = R.mipmap.profilephoto,
        title = "Indonesian chicken burger company",
        address = "Bostancı mah. nur sokak, no 44, daire 3, Kadıköy, İstanbul, Türkiye",
        content = "<b>6856897584</b> Vergi Numarası\n<b>Brandenburg</b> Vergi Dairesi",
        webaddress = "www.itpturkey.com",
        email = "info@itpturkey.com",
        taglist = listOf<String>("Ahşap", "Cam", "Plastik"),
    ),
    MyStoreItem(
        id = 3,
        image = R.mipmap.profilephoto,
        title = "Indonesian chicken burger company",
        address = "Bostancı mah. nur sokak, no 44, daire 3, Kadıköy, İstanbul, Türkiye",
        content = "<b>6856897584</b> Vergi Numarası\n<b>Brandenburg</b> Vergi Dairesi",
        webaddress = "www.itpturkey.com",
        email = "info@itpturkey.com",
        taglist = listOf<String>("Ahşap", "Cam", "Plastik"),
    ),
    MyStoreItem(
        id = 4,
        image = R.mipmap.profilephoto,
        title = "Indonesian chicken burger company",
        address = "Bostancı mah. nur sokak, no 44, daire 3, Kadıköy, İstanbul, Türkiye",
        content = "<b>6856897584</b> Vergi Numarası\n<b>Brandenburg</b> Vergi Dairesi",
        webaddress = "www.itpturkey.com",
        email = "info@itpturkey.com",
        taglist = listOf<String>("Ahşap", "Cam", "Plastik"),
        isactive = false
    ),
)

data class ContractedSupplierItem(
    val id: Int,
    val image: Int,
    val code: String,
    val title: String,
    val content: String,
    val address: String,
    val webaddress: String,
    val email: String,
    val isapproved: Boolean = true,
    val isactive: Boolean = true,
)

val listofContractedSupplier = listOf<ContractedSupplierItem>(
    ContractedSupplierItem(
        id = 1,
        image = R.mipmap.profilephoto,
        code = "XTRGOK",
        title = "Indonesian chicken burger company",
        content = "6856897584 Vergi Numarası\nBrandenburg Vergi Dairesi",
        address = "Bostancı mah. nur sokak, no 44, daire 3, Kadıköy, İstanbul, Türkiye",
        webaddress = "www.itpturkey.com",
        email = "info@itpturkey.com"
    ),
    ContractedSupplierItem(
        id = 2,
        image = R.mipmap.profilephoto,
        code = "XTRGOK",
        title = "Indonesian chicken burger company",
        content = "6856897584 Vergi Numarası\nBrandenburg Vergi Dairesi",
        address = "Bostancı mah. nur sokak, no 44, daire 3, Kadıköy, İstanbul, Türkiye",
        webaddress = "www.itpturkey.com",
        email = "info@itpturkey.com"
    ),
    ContractedSupplierItem(
        id = 3,
        image = R.mipmap.profilephoto,
        code = "XTRGOK",
        title = "Indonesian chicken burger company",
        content = "6856897584 Vergi Numarası\nBrandenburg Vergi Dairesi",
        address = "Bostancı mah. nur sokak, no 44, daire 3, Kadıköy, İstanbul, Türkiye",
        webaddress = "www.itpturkey.com",
        email = "info@itpturkey.com"
    ),
    ContractedSupplierItem(
        id = 4,
        image = R.mipmap.profilephoto,
        code = "XTRGOK",
        title = "Indonesian chicken burger company",
        content = "6856897584 Vergi Numarası\nBrandenburg Vergi Dairesi",
        address = "Bostancı mah. nur sokak, no 44, daire 3, Kadıköy, İstanbul, Türkiye",
        webaddress = "www.itpturkey.com",
        email = "info@itpturkey.com",
        isactive = false
    )
)
data class PanelActiveOfferItem(val id:Int, val date:String, val image:Int, val title:String="", val status:OrderStagesStatus=OrderStagesStatus.BEKLIYOR)
val listofPanelActiveOffer= listOf<PanelActiveOfferItem>(
    PanelActiveOfferItem(1,"31/02/2023",R.mipmap.cocktails,"Indonesian chicken burger",OrderStagesStatus.ODEMEASAMASINDA),
    PanelActiveOfferItem(2,"31/02/2023",R.mipmap.spagetti_01,"Home made cute pancake",OrderStagesStatus.URETIMDE),
    PanelActiveOfferItem(3,"31/02/2023",R.mipmap.hamburger_01,"How to make seafood fried r...",OrderStagesStatus.URETIMDE),
)
data class PanelCollectionItem(val id:Int, val date:String, val image:Int, val title:String="", val status:OrderStagesStatus=OrderStagesStatus.BEKLIYOR)
val listofPanelCollection= listOf<PanelCollectionItem>(
    PanelCollectionItem(1,"31/02/2023",R.mipmap.ac_cocktails,"Indonesian chicken burger",OrderStagesStatus.ODEMEASAMASINDA),
    PanelCollectionItem(2,"31/02/2023",R.mipmap.ac_cocktail2,"Home made cute pancake",OrderStagesStatus.URETIMDE),
    PanelCollectionItem(3,"31/02/2023",R.mipmap.ac_hamburger,"How to make seafood fried r...",OrderStagesStatus.URETIMDE),
    PanelCollectionItem(4,"31/02/2023",R.mipmap.ac_cocktail2,"How to make seafood fried 2",OrderStagesStatus.BEKLIYOR),
)

val listofPanelCollectionSub= listOf<PanelCollectionItem>(
    PanelCollectionItem(1,"31/02/2023",R.mipmap.cocktails,"Indonesian chicken burger",OrderStagesStatus.ODEMEASAMASINDA),
    PanelCollectionItem(2,"31/02/2023",R.mipmap.fries,"Home made cute pancake",OrderStagesStatus.URETIMDE),
    PanelCollectionItem(3,"31/02/2023",R.mipmap.breakfast,"How to make seafood fried r...",OrderStagesStatus.URETIMDE),
)

data class PanelOfferDraftItem(val id:Int, val date:String, val image:Int, val title:String="", val status:OrderStagesStatus=OrderStagesStatus.BEKLIYOR)
val listofPanelOfferDraft= listOf<PanelOfferDraftItem>(
    PanelOfferDraftItem(2,"31/02/2023",R.mipmap.spagetti_01,"Home made cute pancake",OrderStagesStatus.ODEMEASAMASINDA),
    PanelOfferDraftItem(3,"31/02/2023",R.mipmap.spagetti,"How to make seafood fried r...",OrderStagesStatus.URETIMDE),
    PanelOfferDraftItem(4,"31/02/2023",R.mipmap.breakfast,"Indonesian chicken burger",OrderStagesStatus.YOLDA),
    PanelOfferDraftItem(5,"31/02/2023",R.mipmap.hamburger_01,"Home made cute pancake",OrderStagesStatus.URETIMDE),
    PanelOfferDraftItem(6,"31/02/2023",R.mipmap.cocktails,"How to make seafood fried r...",OrderStagesStatus.URETIMDE),
)

data class PanelOfferCompletedItem(val id:Int, val date:String, val image:Int, val title:String="", val status:OrderStagesStatus=OrderStagesStatus.BEKLIYOR)
val listofPanelOfferCompleted= listOf<PanelOfferCompletedItem>(
    PanelOfferCompletedItem(2,"31/02/2023",R.mipmap.spagetti_01,"Home made cute pancake",OrderStagesStatus.TESLIMEDILDI),
    PanelOfferCompletedItem(3,"31/02/2023",R.mipmap.spagetti,"How to make seafood fried r...",OrderStagesStatus.TESLIMEDILDI),
    PanelOfferCompletedItem(4,"31/02/2023",R.mipmap.breakfast,"Indonesian chicken burger",OrderStagesStatus.TESLIMEDILDI),
    PanelOfferCompletedItem(5,"31/02/2023",R.mipmap.hamburger_01,"Home made cute pancake",OrderStagesStatus.TESLIMEDILDI),
    PanelOfferCompletedItem(6,"31/02/2023",R.mipmap.cocktails,"How to make seafood fried r...",OrderStagesStatus.TESLIMEDILDI),
)