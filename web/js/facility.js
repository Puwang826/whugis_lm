lm.facility = (function () {
    var popLayer;
    var polygonlayer;
    var clickedLatLng = null;
    function createPopup() {
        map.off('click');
        var poplatlng;
        if(clickedLatLng !== null){
            poplatlng = clickedLatLng;
        }else {
            alert("点击地图即可进行查询");
        }
        if (clickedLatLng !== null) {
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8081/webgis/getfacilityServlet\n', // 更新为您的servlet映射的正确URL
                data: {
                    Lat: clickedLatLng.lat,
                    Lng: clickedLatLng.lng,
                },
                success: function (res) {
                    console.log(res);
                    buildings = [];
                    var popupContent = "<b>距离此点最近的设施：</b><br>";
                    for (var facility in res) {
                        popupContent += "<b>" + facility + "</b> " + res[facility].name + "<br>";
                        buildings.push(res[facility]);
                    }
                    console.log(buildings);
                    renderBuildingsOnMap(buildings);
                    L.popup({className: "start-popup"}).setLatLng(poplatlng)
                        .setContent(popupContent)
                        .openOn(map);
                },
                error: function (xhr, status, error) {
                    console.error('错误:', error);
                }
            });
        }

        map.on('click', function(e) {
            // 获取点击位置的经纬度
            clickedLatLng = e.latlng;
            console.log(clickedLatLng); // 输出经纬度信息
            createPopup();
        });
    }

    function renderBuildingsOnMap(buildings) {
        var markers = [];
        var polygons =[];
        buildings.forEach(function(buildings) {
            var json = JSON.parse(buildings.json);
            var name = buildings.name;
            polygon = L.geoJSON(json);
            polygons.push(polygon);
            nenode = polygon.getBounds()._northEast;
            swnode = polygon.getBounds()._southWest;
            center = [(nenode.lat+swnode.lat)/2, (nenode.lng+swnode.lng)/2];
            var marker = L.marker(center);
            marker.bindPopup(name);
            markers.push(marker);
        });
        clearLayers();
        popLayer = L.layerGroup(markers).addTo(map);
        polygonlayer = L.layerGroup(polygons).addTo(map);
    }

    function clearLayers() {
        if (popLayer) {
            popLayer.clearLayers();
        }
        if (polygonlayer) {
            polygonlayer.clearLayers();
        }
    }
    function clear() {
        map.off('click'); // 移除地图的点击事件监听器
        clearLayers();
    }

    return {
        onload: function () {
            createPopup();
        },
        clear: function () {
            clear();
        }
    };
})();
