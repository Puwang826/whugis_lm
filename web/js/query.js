// 依据名字查询
lm.query = (function () {
    var popLayer;
    var polygonlayer;
    function query(data) {
        $.ajax({
            data: { searchContent: data.field.title },
            type: 'POST',
            url: 'http://localhost:8081/webgis/querybuildingServlet\n',
            success: function(response) {
                // 处理响应，并使用Leaflet在地图上渲染
                console.log(response);
                renderBuildingsOnMap(response);
            },
            error: function(xhr, status, error) {
                console.error('错误:', error);
            }
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
        popLayer = L.layerGroup(markers).addTo(map);
        polygonlayer = L.layerGroup(polygons).addTo(map);
    }




    function clear() {
        if (popLayer) {
            map.removeLayer(popLayer);
            map.removeLayer(polygonlayer);
        }
    }

    return {
        onload: function (data) {
            query(data);
        },
        clear: function () {
            clear();
        }
    }
})();
