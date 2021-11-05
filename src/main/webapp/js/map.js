var map = new kakao.maps.Map(document.getElementById('map'), {
    center: new kakao.maps.LatLng(36.2683, 127.6358),
    level: 14
});

var clusterer = new kakao.maps.MarkerClusterer({
    map: map,
    averageCenter: true,
    minLevel: 10,
    disableClickZoom: true
});

    var markers = j.position.map(function (position) {
        return new kakao.maps.Marker({
            position: new kakao.maps.LatLng(position.lat, position.lng)
        });
    });

//    for (const ele of markers) {
//        kakao.maps.event.addListener(ele, 'click', function () {
//            infowindow.open(map, ele);
//        });
//    }
    clusterer.addMarkers(markers);

kakao.maps.event.addListener(clusterer, 'clusterclick', function (cluster) {

    var level = map.getLevel() - 1;

    map.setLevel(level, { anchor: cluster.getCenter() });
});

/*var iwContent = '<div class="overlaybox">' +
                '    <ul>' +
                '        <li class="up">' +
                '            <span class="title">회사명</span>' +
                '            <span class="content">ㅇㅇㅇㅇㅇㅇㅇㅇ</span>' +
                '        </li>' +
                '        <li>' +
                '            <span class="title">전화번호</span>' +
                '            <span class="content">000-0000-0000</span>' +
                '        </li>' +
                '        <li>' +
                '            <span class="title">분야</span>' +
                '            <span class="content">ㅇㅇㅇㅇㅇㅇ</span>' +
                '            <span class="content">(ㅇㅇ명)</span>' +
                '        </li>' +
                '    </ul>' +
                '</div>';*/

var infowindow = new kakao.maps.InfoWindow({
    //content: iwContent
});