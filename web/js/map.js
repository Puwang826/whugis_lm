// 地图加载
lm = {};
lm.map = (function () {
    function addmap() {
        var osmurl = 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
        var osmAttrib='Map data &copy; OpenStreetMap contributors';
        var osmlayer = L.tileLayer(osmurl,
            {attribution:'&copy; <a href="OpenStreetMaphttps://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'});
        const imageURL = "http://t0.tianditu.gov.cn/img_w/wmts?" +
            "SERVICE=WMTS&REQUEST=GetTile&VERSION=1.0.0&LAYER=img&STYLE=default&TILEMATRIXSET=w&FORMAT=tiles" +
            "&TILEMATRIX={z}&TILEROW={y}&TILECOL={x}" +
            "&tk=f63d2a8c01570dbb33c788e285385c03";
        var tiandituimg = L.tileLayer(imageURL,{
            attribution: "stamen"
        });
        var wmsLayer = L.tileLayer.wms(
            'http://localhost:8081/geoserver/ne/wms', {
                layers: 'range',
                format: 'image/png',
                transparent: true
            }
        );
        wmsLayer.setOpacity(0.3);
        //实例化map对象，一样是全局变量
        window.map = L.map('map', {
            //这里可能要考虑坐标系统一的问题
            center: { lng:  114.3598900, lat:  30.5444200 },
            //初始级别设置：
            zoom: 16,
            layers: [osmlayer,wmsLayer],
            preferCanvas: true,
            zoomControl: false,
        });

        var overlayMaps = {
            "武汉大学边界":wmsLayer
        };

        var baseMaps = {
            "影像图层":tiandituimg,
            "OSM图层": osmlayer,
        };
        // 创建图层管理控件
        L.control.layers(baseMaps, overlayMaps,{
            position:'topright'
        }).addTo(map);
        // 创建缩放控件
        var zoomControl = L.control.zoom(
            {
                position:'topright'
            }
        );
        zoomControl.addTo(map);
        // 创建鹰眼图控件
        var osmlayer2 = new L.TileLayer(osmurl, {minZoom: 0, maxZoom: 13, attribution: osmAttrib });
        var miniMap = new L.Control.MiniMap(osmlayer2, { toggleDisplay: true }).addTo(map);
        //创建绘制控件
        map.pm.addControls({
            position: 'topright',
            drawMarker: true,
            editMode: true,
            dragMode: false ,// 启用拖动模式，允许用户拖动地图
            drawControls: false ,// 启用绘制控件，允许用户选择要绘制的图形类型
            measureControls: false, // 启用测量控件，允许用户测量距离和面积

        });
    }
    return {
        addmap: function () {
            addmap();
        }
    }
})();
