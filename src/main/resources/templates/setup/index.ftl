<#import "../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#assign pageTitle>Inštalácia systému</#assign>
<@pt.genericPage pageTitle=pageTitle>
<div class="container">
    <h1>${pageTitle}</h1>
    <#if finished??>
        hotovo
    <#else>
        <#if initialization??>
            inicializacia
        <#else>
            <form name="facility" action="<@spring.url '/admin/facility/save'/>" method="post">
                <div class="form-group">
                    <label for="facility-name">Názov</label>
                    <input type="text" name="name" class="form-control" id="facility-name" placeholder="Názov">
                </div>
                <div class="form-group">
                    <label for="facility-streetAndNumber">Ulica a číslo</label>
                    <input type="text" name="streetAndNumber" class="form-control" id="facility-streetAndNumber">
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
                        <input type="submit" value="" class="btn btn-success">
                    </div>
                </div>
            </form>
        </#if>
    </#if>
</div>
</@pt.genericPage>