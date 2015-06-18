<#-- @ftlvariable name="facility" type="sk.stuba.fei.team.local.domain.Facility" -->
<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#assign pageTitle>Nastavenie systému</#assign>
<@pt.dashboardPage pageTitle=pageTitle>
<form id="facility" name="facility" method="post">
    <span class="text-muted" style="padding-bottom: 10px">* Povinné údaje</span>

    <div class="form-group">
        <label for="name">Názov*</label>
        <input type="text" name="name" class="form-control" id="name" required value="${facility.name}">
    </div>
    <div class="form-group">
        <label for="streetAndNumber">Ulica a číslo</label>
        <input type="text" name="streetAndNumber" class="form-control" id="streetAndNumber"
               value="${facility.streetAndNumber}">
    </div>
    <div class="form-group">
        <label for="zip">PSČ</label>
        <input type="text" name="zip" class="form-control" id="zip" value="${facility.zip}">
    </div>
    <div class="form-group">
        <label for="city">Mesto</label>
        <input type="text" name="city" class="form-control" id="city" value="${facility.city}">
    </div>

    <h3>Parametre synchronizácie s Globálnym serverom</h3>

    <div class="form-group">
        <label for="clientID">Client ID*</label>
        <input type="text" name="clientID" class="form-control" id="clientID" required value="${facility.clientID}">
    </div>
    <div class="form-group">
        <label for="clientSecret">Client Secret*</label>
        <input type="text" name="clientSecret" class="form-control" id="clientSecret" required
               value="${facility.clientSecret}">
    </div>
    <div class="form-group">
        <label for="username">Meno*</label>
        <input type="text" name="username" class="form-control" id="username" required value="${facility.username}">
    </div>
    <div class="form-group">
        <label for="password">Heslo*</label>
        <input type="text" name="password" class="form-control" id="password" required value="${facility.password}">
    </div>
    <div class="form-group">
        <button id="saveButton" class="btn btn-success ladda-button" data-style="zoom-in" onclick="saveFacility()">
            <span class="ladda-label">
                <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
                Uložiť
            </span>
        </button>
    </div>
</form>
</@pt.dashboardPage>