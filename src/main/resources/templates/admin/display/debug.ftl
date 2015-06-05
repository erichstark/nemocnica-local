<#-- @ftlvariable name="rpiList" type="java.util.List<sk.stuba.fei.team.local.domain.Rpi_Office>" -->
<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#assign pageTitle>DEBUG - Vyvolávanie pacientov</#assign>
<@pt.dashboardPage pageTitle=pageTitle>
<form name="callIn" action="<@spring.url '/admin/display/debug'/>" method="post">
    <div class="form-group">
        <label for="office">Ambulancia</label>
        <input type="text" name="office" id="office" class="form-control">
    </div>
    <div class="form-group">
        <label for="patient">Pacient</label>
        <input type="text" name="patient" id="patient" class="form-control">
    </div>
    <button class="btn btn-default">Odoslať</button>
</form>
</@pt.dashboardPage>