<#--
  云联创威客系统
  Copyright 2015 云联创科技

  author: Billy Zhang (billy_zh@126.com)
-->
<div class="con">
    <div class="g-top">
        <a onclick="getProposals('proposals_${task.id}', ${task.id}, 0, 1)">全部（${task.projectTotal["totalCount"]}）</a>
        <a onclick="getProposals('proposals_${task.id}', ${task.id}, 1, 1)">中标（${task.projectTotal["selectedCount"]}）</a>
        <a onclick="getProposals('proposals_${task.id}', ${task.id}, 2, 1)">已交稿（${task.projectTotal["submittedCount"]}）</a>
        <a onclick="getProposals('proposals_${task.id}', ${task.id}, 3, 1)">未交稿（${task.projectTotal["waitingCount"]}）</a>
    </div>
</div>
<div id="proposals_${task.id}">

</div>
<div class="btn">
    <a href="http://webim.qiao.baidu.com//im/msg?siteid=8020855" target="_blank" class="tz-sa">不满意找管家</a>
    <div class="h20"></div>
    <a href="javascript:void(0);" class="tz-a js-detUpBtn"><span>收起</span></a>
</div>