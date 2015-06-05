<#-- @ftlvariable name="facility" type="sk.stuba.fei.team.local.domain.Facility" -->
<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#if facility.id??>
    <#assign pageTitle>Editácia zariadenia</#assign>
<#else>
    <#assign pageTitle>Pridanie nového zariadenia</#assign>
</#if>
<@pt.dashboardPage pageTitle=pageTitle>
<div class="row">
    <div class="col-md-12">
        <a class="btn btn-info btn-sm" href="<@spring.url '/admin/facility'/>" role="button">Naspäť</a>
    </div>
</div>

<br>

<div class="table-responsive">
    <form name="facility" action="<@spring.url '/admin/facility/save'/>" method="post">
        <#if facility.id??>
        <div class="form-group" style="display: none;">
        <#else>
        <div class="form-group">
        </#if>
            <label for="facility-id">ID</label>
            <input type="text" name="id" class="form-control" id="facility-id" placeholder="ID"
                   value="${facility.id!""}">
        </div>

        <div class="form-group">
            <label for="facility-name">Názov</label>
            <input type="text" name="name" class="form-control" id="facility-name" placeholder="Názov"
                   value="${facility.name!""}">
        </div>
        <div class="form-group">
            <label for="facility-streetAndNumber">Ulica a číslo</label>
            <input type="text" name="streetAndNumber" class="form-control" id="facility-streetAndNumber" placeholder="StreetAndNumber"
                   value="${facility.streetAndNumber!""}">
        </div>
        <div class="form-group">
            <label for="facility-zip">PSČ</label>
            <input type="text" name="zip" class="form-control" id="facility-zip" placeholder="PSČ"
                   value="${facility.zip!""}">
        </div>
        <div class="form-group">
            <label for="facility-city">Mesto</label>
            <input type="text" name="city" class="form-control" id="facility-city" placeholder="City"
                   value="${facility.city!""}">
        </div>

        <div class="form-group">
            <div>
                <input type="submit" value="Ulož" class="btn btn-success">
            </div>
        </div>
    </form>
</div>
</@pt.dashboardPage>