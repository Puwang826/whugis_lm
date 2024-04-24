lm.heatMap = (function () {
    var heatmapLayer;
    function onload() {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8081/webgis/querybuildingServlet',
            data: { searchContent: null },
            success: function(response) {
                // 处理回应，将回应传给热力图渲染函数
                console.log(response);
                createheatMap(response);
            },
            error: function(xhr, status, error) {
                console.error('Error:', error);
            }
        });
    }

    function createheatMap(buildings){
        var centers = [];
        buildings.forEach(function(building) {
            var json = JSON.parse(building.json);
            polygon = L.geoJSON(json);
            nenode = polygon.getBounds()._northEast;
            swnode = polygon.getBounds()._southWest;
            center = [(nenode.lat+swnode.lat)/2, (nenode.lng+swnode.lng)/2];
            centers.push([(nenode.lat+swnode.lat)/2, (nenode.lng+swnode.lng)/2]);
        });
        console.log(centers);
        var heatmapData = centers.map(item => ({
            lat: item[0],
            lng: item[1],
        }));
        heatmapLayer = L.heatLayer(heatmapData, {
            radius: 30,
            maxOpacity: 0.5,
        }).addTo(map);
    }
    // Clean up resources
    function clear() {
        if (heatmapLayer) {
            map.removeLayer(heatmapLayer);
            heatmapLayer = null;
        }
    }

    return {
        onload: function () {
            onload();
        },
        clear: function () {
            clear();
        }
    };
})();
