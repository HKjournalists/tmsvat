
// @Bind #addBtn${childpropertyname}.onClick
!function(self, arg) {

	var data = this.id("dataSet${declarationName}").getData("#.rel${childpropertyname}")
	if (data) {
		// dorado.MessageBox.alert("来自从表toolBarButton添加按键的onClick事件, 补充主表元素");
		data.insert({
			${declarationName}Id : this.id("dataSet${declarationName}").getData("#.id")
		});
		this.id("dialog${childdeclarationName}").show();
	} else {
		dorado.MessageBox.alert("当前没有选中的主表信息，不能添加子表信息！");
	}

}

// @Bind #delBtn${childpropertyname}.onClick
!function(self, arg) {

	var data = this.id("dataSet${declarationName}").getData("#.rel${childpropertyname}");
	if (data.current) {
		var action = this.id("updateActionSave${childdeclarationName}");
		dorado.MessageBox.confirm("您真的要删除当前子表信息吗？", function() {
			data.remove();
			action.execute();
		});
	} else {
		dorado.MessageBox.alert("当前没有子表信息可供删除！");
	}

}

// @Bind #modifyBtn${childpropertyname}.onClick
!function(self, arg) {

	var data = this.id("dataSet${declarationName}").getData("#.rel${childpropertyname}");
	if (data.current) {
		this.id("dialog${childdeclarationName}").show();
	} else {
		dorado.MessageBox.alert("当前没有子表信息可供修改！");
	}

}

// @Bind #dialog${childdeclarationName}.onHide
!function(self, arg) {
	this.id("dataSet${declarationName}").getData("#.rel${childpropertyname}").cancel();

}

// @Bind #saveSingleBtn${childdeclarationName}.onClick
!function(self, arg) {
	var view = this;
	view.id("updateActionSave${childdeclarationName}").execute(function() {
		view.id("dialog${childdeclarationName}").hide();
	});

}

// @Bind #cancelBtn${childdeclarationName}.onClick
!function(self, arg) {
	this.id("dialog${childdeclarationName}").hide();

}