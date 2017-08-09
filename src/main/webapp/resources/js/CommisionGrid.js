$(function () {
	$('#grid').w2grid(config.grid);    
	w2ui.grid.on('click', function(event) {
	    var grid = this;
	    event.onComplete = function () {
	        var sel = grid.getSelection();
	    }
	});
});

var config ={
		grid:{ 
			name: 'grid', 
			header: 'Commision Details',
	        show: { 
	            toolbar: true,
	            footer: true,
	            toolbarAdd: true,
	            toolbarDelete: true,
	            toolbarSave: true,
	            toolbarEdit: true,
	            header : true
	        },
	        url  : {
	            get    : 'getCommision',
	            remove : 'deleteCommision'
	        },
	        method: 'GET',
	        recid: 'id',
	        searches: [                
	        	{ field: 'serviceProvider.name', caption: 'Name', type: 'text' }
	        ],
	        columns: [                
	            { field: 'id', caption: 'ID', size: '20px', sortable: true, attr: 'align=center' },
	            { field: 'serviceProvider.name', caption: 'Name', size: '15%', sortable: true },
	            { field: 'merchantCommision', caption: 'Merchant', size: '10%', sortable: true },
	            { field: 'partnerCommision', caption: 'Partner', size: '10%' },
	            { field: 'expiryDateTime', caption: 'Expiry', size: '10%' },
	            { field: 'mavCommision', caption: 'Mav', size: '10%' },
	            { field: 'reddotbeforeayacutCommision', caption: 'rdn Before AYA', size: '15%' },
	            { field: 'aya', caption: 'aya', size: '10%' },
	            { field: 'rdnMinimumCommission', caption: 'RDN Min.', size: '10%' }
	        ],
	        onReload: function(event) {
	        	w2ui.grid.method = 'GET';
	        	console.log(event);
	        },
	        onRefresh: function(event) {
	        	this.method = 'GET';
	            console.log('object '+ event.target + ' is refreshed');
	        },
	        onAdd: function (event) {
	        	w2utils.settings['dataType'] = 'HTTP';
	            addPopup();
	        },
	        onEdit: function (event) {
	        	w2utils.settings['dataType'] = 'HTTP';
	            editPopup();
	            w2ui.edit.record = $.extend(true, {}, w2ui.grid.get(w2ui.grid.getSelection()[0]));
                w2ui.edit.refresh();
                console.log(this.getSelection());
                var rec = w2ui.edit.record.serviceProvider;
                w2ui.edit.record.serviceProviderId = rec.id;
                delete w2ui.edit.record.serviceProvider;
                delete w2ui.edit.record.lastUpdateDateTime;
                console.log(w2ui.edit.record);
	        },
	        onDelete: function (event) {
	        	w2utils.settings['dataType'] = 'HTTP';
	        	w2ui.grid.method = 'POST';
	        	event.onComplete = function () {
	        		w2ui.grid.method = 'GET';
	        		console.log(event);
	    	    };
	        },
	        onSave: function (event) {
	            w2alert('save');
	        }
	    },
	    editForm: {
	        name: 'edit',
	        style: 'border: 0px; background-color: transparent;',
	        recid: 10,
	        url      : {
//	            get  : 'getCommision/:id',
	            save : 'updateCommision' 
	        },
//	        method: 'GET',
	        fields : [
	            { field: 'merchantCommision',   type: 'float',required: true, html: { caption: 'Merchant Commision', page: 0, attr: 'style="width: 300px; ' } },
	            { field: 'partnerCommision',   type: 'float',required: true, html: { caption: 'Partner Commision', page: 0, attr: 'style="width: 300px; ' } },
	            { field: 'isActive',   type: 'toggle',required: true, html: { caption: 'Active', page: 0 } },
	            { field: 'mavCommision',   type: 'float',required: true, html: { caption: 'Mav Commision', page: 0, attr: 'style="width: 300px; ' } },
	            { field: 'reddotbeforeayacutCommision',   type: 'float',required: true, html: { caption: 'RED DOT before aya cut Commision', page: 0, attr: 'style="width: 300px; ' } },
	            { field: 'aya',   type: 'float',required: true, html: { caption: 'aya', page: 0, attr: 'style="width: 300px; ' } },
	            { field: 'rdnMinimumCommission',   type: 'float',required: true, html: { caption: 'RDN Minimum Commision', page: 0, attr: 'style="width: 300px; ' } },
	            
	       ],
	       tabs: [
	           { id: 'commisionTab', caption: 'Commision'}
	       ],
	        actions: {
	            "save": function () {
	            	if(this.validate().length == 0){
	            		this.save();
	            		$().w2popup('close', editPopupForm);
	            		w2ui.grid.refresh();
	            		w2ui.grid.reload();
	            	}else{
	            		this.save();
	            	}
	        	 },
	            "reset": function () { this.clear(); }
	        },
	        onRequest: function(event,data) {
	            w2ui.edit.routeData.id =  w2ui.grid.getSelection();
	            
	        }
	    }
	};

var tmp ;
var addPopupForm = {
        title   : 'Form in a Popup',
        body    : '<div id="form" style="width: 100%; height: 100%;"></div>',
        style   : 'padding: 15px 0px 0px 0px',
        width   : 500,
        height  : 500, 
        showMax : true,
        onToggle: function (event) {
            $(w2ui.foo.box).hide();
            event.onComplete = function () {
                $(w2ui.foo.box).show();
                w2ui.foo.resize();
            }
        },
        onOpen: function (event) {
            event.onComplete = function () {
                // specifying an onOpen handler instead is equivalent to specifying an onBeforeOpen handler, which would make this code execute too early and hence not deliver.
                $('#w2ui-popup #form').w2render('foo');
            }
        }
    };

var editPopupForm = {
        title   : 'Form in a Popup',
        body    : '<div id="form" style="width: 100%; height: 100%;"></div>',
        style   : 'padding: 15px 0px 0px 0px',
        width   : 500,
        height  : 500, 
        showMax : true,
        onToggle: function (event) {
            $(w2ui.edit.box).hide();
            event.onComplete = function () {
                $(w2ui.edit.box).show();
                w2ui.edit.resize();
            }
        },
        onOpen: function (event) {
            event.onComplete = function () {
                // specifying an onOpen handler instead is equivalent to specifying an onBeforeOpen handler, which would make this code execute too early and hence not deliver.
                $('#w2ui-popup #form').w2render('edit');
            }
        }
    };

var addForm = {
        name: 'foo',
        style: 'border: 0px; background-color: transparent;',
        url      : 'addCommision',
        fields : [
            { field: 'generalStatusId', type: 'int', required: true, html: { caption: 'General Status Id', page: 0, attr: 'style="width: 300px"' } },
            { field: 'currencyId',  type: 'int', required: true, html: { caption: 'Currency Id', page: 0, attr: 'style="width: 300px"' } },
            { field: 'serviceProviderId',   type: 'int', required: true, html: { caption: 'Service Provider Id', page: 0, attr: 'style="width: 300px; ' } },
            { field: 'name',   type: 'text',required: true, html: { caption: 'Name', page: 0, attr: 'style="width: 300px;' } },
            { field: 'contactEmail',   type: 'email', html: { caption: 'Contact Email', page: 0, attr: 'style="width: 300px; ' } },
            { field: 'supportEmail',   type: 'email', html: { caption: 'Support Email', page: 0, attr: 'style="width: 300px; ' } },
            { field: 'hasCustomStatus',   type: 'toggle', html: { caption: 'Custom Status' , page: 0 } },
            { field: 'settlementMode',   type: 'text', required: true, html: { caption: 'Settlement Mode', page: 0, attr: 'style="width: 300px; ' } },
            { field: 'timezone',   type: 'text', html: { caption: 'timezone', page: 0, attr: 'style="width: 300px; ' } },
            { field: 'merchantCommision',   type: 'float',required: true, html: { caption: 'Merchant Commision', page: 1, attr: 'style="width: 300px; ' } },
            { field: 'partnerCommision',   type: 'float',required: true, html: { caption: 'Partner Commision', page: 1, attr: 'style="width: 300px; ' } },
            { field: 'isActive',   type: 'toggle',required: true, html: { caption: 'Active', page: 1 } },
            { field: 'mavCommision',   type: 'float',required: true, html: { caption: 'Mav Commision', page: 1, attr: 'style="width: 300px; ' } },
            { field: 'reddotbeforeayacutCommision',   type: 'float',required: true, html: { caption: 'RED DOT before aya cut Commision', page: 1, attr: 'style="width: 300px; ' } },
            { field: 'aya',   type: 'float',required: true, html: { caption: 'aya', page: 1, attr: 'style="width: 300px; ' } },
            { field: 'rdnMinimumCommission',   type: 'float',required: true, html: { caption: 'RDN Minimum Commision', page: 1, attr: 'style="width: 300px; ' } },
            
       ],
       postData: this.tmp,
       tabs: [
           { id: 'serviceProviderTab', caption: 'Service Provider' },
           { id: 'commisionTab', caption: 'Commision'}
       ],
        actions: {
            "save": function () {
            	if(this.validate().length == 0){
            		this.save();
            		$().w2popup('close', addPopupForm);
            		w2ui.grid.refresh();
            		w2ui.grid.reload();
            	}else{
            		this.save();
            	}
        	 },
            "reset": function () { this.clear(); }
        }
    };

function addPopup(){
	if (!w2ui.foo) {
        $().w2form(addForm);
    }
	$().w2popup('open', addPopupForm);

	w2ui.foo.on('submit', function(event, data) {
		var tmp1 = data.postData['record'];
		delete data.postData['record'];
	    data.postData = tmp1;
	});
}

function editPopup(){
	if (!w2ui.edit) {
        $().w2form(config.editForm);
    }
	$().w2popup('open', editPopupForm);

	w2ui.edit.on('submit', function(event, data) {
		console.log(data.postData['record']);
		tmp = data.postData['record'];
		delete data.postData['record'];
	    data.postData = tmp;
	});
}
 

