import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet'

//rfce
function Map() {
  return (
    <div>

        <MapContainer className="map" center={[59.436962, 24.753574]} zoom={13} scrollWheelZoom={true}>
        <TileLayer
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        <Marker position={[59.436, 24.755]}>
            <Popup>
            Viru Keskus <br /> Avatud 09:00 - 22:00
            </Popup>
        </Marker>
        <Marker position={[59.421, 24.793]}>
            <Popup>
            Ülemiste Keskus <br /> Avatud 09:00 - 22:00
            </Popup>
        </Marker>
        <Marker position={[59.427, 24.723]}>
            <Popup>
            Kristiine Keskus <br /> Avatud 09:00 - 22:00
            </Popup>
        </Marker>
        </MapContainer>
    </div>
  )
}

export default Map