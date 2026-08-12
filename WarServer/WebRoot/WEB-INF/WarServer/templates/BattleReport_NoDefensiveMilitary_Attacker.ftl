由于对方城市没有设定留守军队，您的军队长驱直入，成功攻陷城市。战斗胜利，您的军队未受到任何损失。<br />
掠夺资源： 木材:${woodNum} 钢铁:${steelNum} 石油:${oilNum} 食物:${foodNum} 金钱:${moneyNum}<br />
由于我方战斗胜利，成功降低敌方城市 ${minusSecurity} 点治安值。
<#if haveColonized==1>由于您将对方城市攻陷并且对方城市治安值过低，您已成功殖民该城市。</#if><#if haveColonized==2>由于您已经殖民该城市，本次战斗未进行任何殖民任务。</#if><#if haveColonized==3>由于您目前所能殖民的城市数量已达上限，本次战斗未进行任何殖民任务。</#if>