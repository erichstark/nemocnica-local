<#-- @ftlvariable name="patients" type="sk.stuba.fei.team.local.domain.Patient[]" -->
<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#assign pageTitle>Administracia pacientov</#assign>
<@pt.dashboardPage pageTitle=pageTitle>
<div class="row">
    <div class="col-md-12">
        <a class="btn btn-info btn-sm" href="<@spring.url '/admin/patient/add'/>" role="button">Vytvoriť
            pacienta</a>
    </div>
</div>

<h2 class="sub-header">Zoznam pacientov</h2>

<div class="row">
    <div class="col-md-12">
        <form class="form-inline" method="POST" action="<@spring.url '/admin/patient/search'/>">
            <div class="form-group">
                <label for="text">Vyhľadanie:</label>
                <input type="text" name="text" class="form-control" id="text"
                       placeholder="Zadaj meno alebo prizevisko"
                       value="${search!""}" style="width: 400px;">
            </div>
            <input type="submit" value="Hľadaj" class="btn btn-default">
            <a class="btn btn-default" href="<@spring.url '/admin/patient/clear'/>">Zruš</a>
        </form>
    </div>
</div>

<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th style="width: 60px;">#</th>
            <th>ID</th>
            <th>Meno pacienta</th>
            <th>Telefón</th>
            <th>Email</th>
            <th>Poisťovňa</th>
            <th style="width: 60px;">Akcia</th>
        </tr>
        </thead>
        <tbody>
            <#list patients as patient>
            <tr>
                <td>${patient_index + 1}</td>
                <td>${patient.id}</td>
                <td>
                    <a href="<@spring.url '/admin/patient/edit/' + patient.id />">
                    ${patient.prefix_title + ' ' + patient.firstName + ' ' + patient.surname}
                    <#if patient.suffix_title?length gt 0>${', ' + patient.suffix_title}</#if>
                    </a>
                </td>
                <td>${patient.phone!""}</td>
                <td>${patient.email!""}</td>
                <td>${patient.insurance.name}</td>
                <td><a href="<@spring.url '/admin/patient/delete/' + patient.id />"
                       onclick="return confirm('Naozaj?');">Zmazať</a></td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>
</@pt.dashboardPage>