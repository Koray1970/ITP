package com.isticpla.itp.offers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.isticpla.itp.offers.ui.*
import com.isticpla.itp.uimodules.AppColors

@Composable
fun ProposalWizardStage(level: Int,label:String) = Column(
    modifier= Modifier
        .fillMaxWidth()
) {
    val wizardlevel=4
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = label, style = offerWizardPageTitle)
        Spacer(modifier=Modifier.weight(1f))
        Text(text = "${level+1}/$wizardlevel", style = offerWizardLevelCounter)
    }
    Spacer(modifier=Modifier.height(3.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
    ) {
        var bColor = AppColors.grey_127
        repeat(wizardlevel) { i ->
            if (level == i)
                bColor = AppColors.yellow_103
            else
                bColor = AppColors.grey_127
            Box(
                modifier = Modifier
                    .background(bColor)
                    .weight(.25f)
                    .requiredHeight(8.dp)
            )
        }
    }
    Spacer(modifier=Modifier.height(26.dp))
}