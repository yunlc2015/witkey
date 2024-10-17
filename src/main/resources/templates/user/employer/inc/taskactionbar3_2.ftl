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
<div id="proposals_${task.id}" runat="server">

</div>