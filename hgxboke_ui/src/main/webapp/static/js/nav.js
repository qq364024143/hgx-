
/**
 * 当前导航栏下划线
 */
var obj=null;
var As=document.getElementById('topnav').getElementsByTagName('a');
for(i=0;i<As.length;i++){
	if(window.location.href.indexOf(As[i].href)>=0){
		obj=As[i];
		obj.id='topnav_current';
	}
}
