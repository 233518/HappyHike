package OnMapReadyCallbackcom.example.happyhike.controllers

import com.example.happyhike.R
import com.example.happyhike.guis.KartGui
import com.example.happyhike.scenes.KartScene
import com.example.happyhike.systems.map.MapSystem
import com.google.android.gms.maps.SupportMapFragment

class KartController(kartScene : KartScene) {
    //Init later
    private lateinit var kartGui: KartGui
    lateinit var mapSystem: MapSystem

    //Init now
    var kartScene = kartScene;

    init {
        kartGui = KartGui()
        mapSystem = MapSystem(kartScene.supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment)
    }
}