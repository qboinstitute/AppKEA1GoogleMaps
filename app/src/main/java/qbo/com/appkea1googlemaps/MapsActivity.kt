package qbo.com.appkea1googlemaps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerDragListener {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapClickListener(this)
        mMap.setOnMarkerDragListener(this)
        // Add a marker in Sydney and move the camera
        val posicionInicial = LatLng(-17.964608, -67.106727)
        mMap.addMarker(MarkerOptions()
                .position(posicionInicial)
                .draggable(true)
                .snippet("Ubicación")
                //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location))
                .title("Oruro-Bolivia"))
        mMap.isTrafficEnabled = true
        //mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicionInicial,
                16.0F))
    }

    override fun onMapClick(p0: LatLng?) {
        mMap.addMarker(MarkerOptions().position(p0!!)
                .title("Nuevo Marcador")
                .draggable(true))
        mMap.animateCamera(CameraUpdateFactory.newLatLng(p0!!))
    }

    override fun onMarkerDragStart(p0: Marker?) {
        p0!!.hideInfoWindow()
    }

    override fun onMarkerDrag(p0: Marker?) {

    }

    override fun onMarkerDragEnd(p0: Marker?) {
       var posicion = p0!!.position
        p0!!.snippet = posicion.latitude.toString() +" - " +
                posicion.longitude.toString()
        p0!!.showInfoWindow()
    }
}