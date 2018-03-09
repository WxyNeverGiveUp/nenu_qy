$(function(){
    var valueName = $("#whereaboutgoId").children("option:selected").val();
    if(valueName==10||valueName==11||valueName==12) {
        $(".control-area1").addClass("required").children("input").addClass("J_notNull");
        $(".control-area2").addClass("required").children("input").addClass("J_notNull");
        $(".control-area3").addClass("required").children("#J_unitAreaHolder").addClass("J_notNull");
        $(".control-area5").addClass("required").find("select").addClass('J_notOption');
        $(".control-area6").addClass("required").find("select").addClass('J_notOption');
        $(".control-area8").addClass("required").find("select").addClass('J_notOption');
    }
    if(valueName==27||valueName==50||valueName==51||valueName==76) {
        $(".control-area1").addClass("required").children("input").addClass("J_notNull");
        $(".control-area3").addClass("required").children("#J_unitAreaHolder").addClass("J_notNull");
    }
    if(valueName==75) {
        $(".control-area1").addClass("required").children("input").addClass("J_notNull");
        $(".control-area3").addClass("required").children("#J_unitAreaHolder").addClass("J_notNull");
        $(".control-area6").addClass("required").find("select").addClass('J_notOption');
    }
    if(valueName==80||valueName==85) {
        $(".control-area1").addClass("required").children("input").addClass("J_notNull");
    }
	$("#whereaboutgoId").change(function(){
		var valueName = $(this).find("option:selected").val();
		$(".officeMsg").children("div").removeClass('required').children("input").removeClass('J_notNull');
		$(".officeMsg").children().children("select").removeClass('J_notOption');
		$("#whereaboutgoId").parent().addClass("required");
		if(valueName==10||valueName==11||valueName==12) {
			$(".control-area1").addClass("required").children("input").addClass("J_notNull");
			$(".control-area2").addClass("required").children("input").addClass("J_notNull");
			$(".control-area3").addClass("required").children("#J_unitAreaHolder").addClass("J_notNull");
			$(".control-area5").addClass("required").find("select").addClass('J_notOption');
			$(".control-area6").addClass("required").find("select").addClass('J_notOption');
			$(".control-area8").addClass("required").find("select").addClass('J_notOption');
		}
		if(valueName==27||valueName==50||valueName==51||valueName==76) {
			$(".control-area1").addClass("required").children("input").addClass("J_notNull");
			$(".control-area3").addClass("required").children("#J_unitAreaHolder").addClass("J_notNull");
		}
		if(valueName==75) {
			$(".control-area1").addClass("required").children("input").addClass("J_notNull");
			$(".control-area3").addClass("required").children("#J_unitAreaHolder").addClass("J_notNull");
			$(".control-area6").addClass("required").find("select").addClass('J_notOption');
		}
		if(valueName==80||valueName==85) {
			$(".control-area1").addClass("required").children("input").addClass("J_notNull");
		}
        $(".officeMsg input").val("");
        $(".officeMsg select").val("-1");
        $(this).val(valueName);

    })
	
});
