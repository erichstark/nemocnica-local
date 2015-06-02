<#-- @ftlvariable name="rpiList" type="java.util.List<sk.stuba.fei.team.local.domain.Rpi_Office>" -->
<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#assign pageTitle>Testovanie vyvolávania</#assign>
<@pt.dashboardPage pageTitle=pageTitle>
<div class="row">
    <div class="col-md-12">
        tu budu nejake globalne nastavenia obrazoviek...
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <a class="btn btn-info btn-sm" href="<@spring.url '/admin/rpiconfig/add'/>" role="button">Pridať obrazovku</a>
    </div>
</div>

</@pt.dashboardPage>