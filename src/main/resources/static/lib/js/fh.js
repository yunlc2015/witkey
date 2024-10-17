var h = {
    init: function () {
        var me = this;

        me.loaded = 0;
        //每次读取1M  
        me.step = 1024 * 1024;
        me.times = 0;
    },
    fileHandler: function (e) {
        var me = h;

        var file = me.file = this.files[0];

        var reader = me.reader = new FileReader();

        //  
        me.total = file.size;

        reader.onloadstart = me.onLoadStart;
        reader.onprogress = me.onProgress;
        reader.onabort = me.onAbort;
        reader.onerror = me.onerror;
        reader.onload = me.onLoad;
        reader.onloadend = me.onLoadEnd;
        //读取第一块  
        me.readBlob(file, 0);
    },
    onLoadStart: function () {
        var me = h;
    },
    onProgress: function (e) {
        var me = h;

        me.loaded += e.loaded;
        //更新进度条  
        me.progress.value = (me.loaded / me.total) * 100;
    },
    onError: function () {
        var me = h;

    },
    onLoad: function () {
        var me = h;

        if (me.loaded < me.total) {
            me.readBlob(me.loaded);
        } else {
            me.loaded = me.total;
        }
    },
    onLoadEnd: function () {
        var me = h;

    },
    readBlob: function (start) {
        var me = h;

        var blob,
            file = me.file;

        me.times += 1;

        if (file.webkitSlice) {
            blob = file.webkitSlice(start, start + me.step + 1);
        } else if (file.mozSlice) {
            blob = file.mozSlice(start, start + me.step + 1);
        }

        me.reader.readAsText(blob);
    }
};

h.init();