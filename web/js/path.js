lm.path = (function () {
    var startPointMarker = null;
    var endPointMarker = null;
    var PathLayer;

    function onload() {
        // 提示用户选择起点
        alert("请选择路径规划的起点");
        map.on('click', handleMapClick);
    }

    function handleMapClick(e) {
        var clickedPoint = e.latlng;
        if (!startPointMarker) {
            startPointMarker = L.circleMarker(clickedPoint, { radius: 5, color: 'green' }).addTo(map);
            // 提示用户选择终点
            alert("已选择起点，请选择终点");
        } else if (!endPointMarker) {
            endPointMarker = L.circleMarker(clickedPoint, { radius: 5, color: 'red' }).addTo(map);
            console.log(startPointMarker.getLatLng(), endPointMarker.getLatLng());
            sendPointsToServer(startPointMarker.getLatLng(), endPointMarker.getLatLng());
        }
    }

    function sendPointsToServer(start, end) {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8081/webgis/getpathServlet', // 更新为您的服务器 URL
            data: {
                startLat: start.lat,
                startLng: start.lng,
                endLat: end.lat,
                endLng: end.lng,
            },
            success: function (response) {
                console.log(response);
                renderShortestPath(response);
                // 提示用户分析成功
                alert("路径规划成功！如需要再次进行路径规划，请再次点击路径规划功能");
            },
            error: function (xhr, status, error) {
                console.error('Error:', error);
                // 提示用户分析失败
                alert("路径规划失败，请稍后再试。");
            }
        });
    }

    function renderShortestPath(jsonData) {
        if (PathLayer) {
            map.removeLayer(PathLayer);
        }
        // 创建多段线并添加到地图上
        PathLayer = new L.GeoJSON(JSON.parse(jsonData)).addTo(map);
    }

    function clear() {
        map.off('click'); // 移除地图的点击事件监听器
        if (PathLayer) {
            map.removeLayer(PathLayer);
            PathLayer = null;
        }
        if (startPointMarker) {
            map.removeLayer(startPointMarker);
            startPointMarker = null;
        }
        if (endPointMarker) {
            map.removeLayer(endPointMarker);
            endPointMarker = null;
        }
    }

    return {
        onload: onload,
        clear: clear
    };
})();
