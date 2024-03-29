<#-- @ftlvariable name="insurances" type="sk.stuba.fei.team.local.domain.Insurance[]" -->
<#-- @ftlvariable name="patient" type="sk.stuba.fei.team.local.domain.Patient" -->
<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#if patient.username?? && patient.username?has_content>
    <#assign pageTitle>Editácia pacienta</#assign>
<#else>
    <#assign pageTitle>Pridanie nového pacienta</#assign>
</#if>
<@pt.dashboardPage pageTitle=pageTitle>

<div class="row">
    <div class="col-md-12">
        <a class="btn btn-info btn-sm" href="<@spring.url '/admin/patient'/>" role="button">Naspäť</a>
    </div>
</div>

<br>
    <#if insurances?size == 0>
    <h2>Najskôr treba vytvoriť poisťovňu!</h2>
    <br>
    <a class="btn btn-warning btn-sm" href="<@spring.url '/admin/insurance/add'/>" role="button">Pridať poisťovňu</a>
    <#else>
    <div class="table-responsive">
        <form name="patient" action="<@spring.url '/admin/patient/save'/>" method="post">
            <#if patient.username?? && patient.username?has_content>
                <input type="hidden" name="username" class="form-control" id="patient-username" value="${patient.username}">
                <input type="hidden" name="password" class="form-control" id="patient-password" value="${patient.password}">
            <#else>
                <div class="form-group">
                    <label for="patient-username">Používateľské meno</label>
                    <input type="text" name="username" class="form-control" id="patient-username" placeholder="Používateľské meno">
                </div>
                <div class="form-group">
                    <label for="patient-password">Heslo</label>
                    <input type="text" name="password" class="form-control" id="patient-password" placeholder="Heslo">
                </div>
            </#if>
            <div class="input-group has-warning">
                <span class="input-group-addon">
                    <input type="checkbox" name="enabled" value="true" <#if  patient.enabled>checked</#if> >
                </span>
                <span class="form-control">Aktivovaný účet</span>
            </div>
            <div class="form-group">
                <label for="patient-prefix_title">Titul pred</label>
                <input type="text" name="prefix_title" class="form-control" id="patient-prefix_title"
                       placeholder="Titul pred"
                       value="${patient.prefix_title!""}">
            </div>
            <div class="form-group">
                <label for="patient-firstName">Meno</label>
                <input type="text" name="firstName" class="form-control" id="patient-firstName" placeholder="Meno"
                       value="${patient.firstName!""}">
            </div>
            <div class="form-group">
                <label for="patient-surname">Priezvisko</label>
                <input type="text" name="surname" class="form-control" id="patient-surname" placeholder="Priezvisko"
                       value="${patient.surname!""}">
            </div>
            <div class="form-group">
                <label for="patient-suffix_title">Titul za</label>
                <input type="text" name="suffix_title" class="form-control" id="patient-suffix_title"
                       placeholder="Titul za"
                       value="${patient.suffix_title!""}">
            </div>
            <div class="form-group">
                <label for="patient-phone">Telefón</label>
                <input type="text" name="phone" class="form-control" id="patient-phone" placeholder="Telefón"
                       value="${patient.phone!""}">
            </div>
            <div class="form-group">
                <label for="patient-email">Email</label>
                <input type="email" name="email" class="form-control" id="patient-email" placeholder="Email"
                       value="${patient.email!""}">
            </div>
            <div class="form-group">
                <label for="pationt-insurance">Poisťovňa</label>
                <select name="insurance" class="form-control" id="pationt-insurance">
                    <#list insurances as insurance>
                        <option value="${insurance.id}">${insurance.name}</option>
                    </#list>
                </select>
            </div>

            <div class="form-group">
                <div>
                    <input type="submit" value="Ulož" class="btn btn-success">
                </div>
            </div>

        </form>
    </div>
    </#if>
</@pt.dashboardPage>