<#-- @ftlvariable name="offices" type="sk.stuba.fei.team.local.domain.Office[]" -->
<#-- @ftlvariable name="employee" type="sk.stuba.fei.team.local.domain.Employee" -->
<#-- @ftlvariable name="searchResults" type="java.util.Collection<sk.stuba.fei.team.local.domain.Specialization>" -->
<#-- @ftlvariable name="searchTerm" type="java.lang.String" -->
<#import "../../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<#if !employee.username?has_content>
    <#assign pageTitle>Pridanie nového zamestnanca</#assign>
<#else>
    <#assign pageTitle>Editácia zamestnanca</#assign>
</#if>
<@pt.dashboardPage pageTitle=pageTitle>
<div class="row">
    <div class="col-md-12">
        <a class="btn btn-info btn-sm" href="<@spring.url '/admin/employee'/>" role="button">Naspäť</a>
    </div>
</div>

<br>

<div class="panel-body" id="form">
    <div class="row">
        <div class="form-group col-md-6 col-xs-6 col-sm-6">
            <label for="employee-username">Prihlasovacie meno</label>
            <input type="text" name="username" class="form-control" id="employee-username"
                   placeholder="Prihlasovacie meno"
                   value="${employee.username!""}" <#if employee.username?has_content>disabled</#if>>
        </div>
        <div class="form-group col-md-6 col-xs-6 col-sm-6">
            <label for="patient-password">Heslo</label>
            <input type="password" name="password" class="form-control" id="patient-password" placeholder="Heslo"
                   value="${employee.password!""}">
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-6 col-xs-6 col-sm-6">
            <label for="employee-prefix_title">Titul pred</label>
            <input type="text" name="prefix_title" class="form-control" id="employee-prefix_title"
                   placeholder="Titul pred"
                   value="${employee.prefix_title!""}">
        </div>
        <div class="form-group col-md-6 col-xs-6 col-sm-6">
            <label for="employee-suffix_title">Titul za</label>
            <input type="text" name="suffix_title" class="form-control" id="employee-suffix_title"
                   placeholder="Titul za"
                   value="${employee.suffix_title!""}">
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-6 col-xs-6 col-sm-6">
            <label for="employee-firstName">Meno</label>
            <input type="text" name="firstName" class="form-control" id="employee-firstName" placeholder="Meno"
                   value="${employee.firstName!""}">
        </div>
        <div class="form-group col-md-6 col-xs-6 col-sm-6">
            <label for="employee-surname">Priezvisko</label>
            <input type="text" name="lastName" class="form-control" id="employee-surname" placeholder="Priezvisko"
                   value="${employee.lastName!""}">
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-6 col-xs-6 col-sm-6">
            <div class="input-group has-warning">
                <span class="input-group-addon">
                    <input type="checkbox" name="enabled" value="true" <#if  employee.enabled>checked</#if> >
                </span>
                <span class="form-control">Aktivovaný účet</span>
            </div>
        </div>
        <div class="form-group col-md-6 col-xs-6 col-sm-6">
            <div class="input-group">
                <span class="input-group-addon">
                    <input type="checkbox" name="accountNonExpired" value="true"
                           <#if  employee.accountNonExpired>checked</#if> >
                </span>
                <span class="form-control">Platný účet (non expired)</span>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-6 col-xs-6 col-sm-6">
            <div class="input-group">
                <span class="input-group-addon">
                    <input type="checkbox" name="accountNonLocked" value="true"
                           <#if employee.accountNonLocked>checked</#if> >
                </span>
                <span class="form-control">Odomknutý účet (non locked)</span>
            </div>
        </div>
        <div class="form-group col-md-6 col-xs-6 col-sm-6">
            <div class="input-group">
                <span class="input-group-addon">
                    <input type="checkbox" name="credentialsNonExpired" value="true"
                           <#if  employee.credentialsNonExpired>checked</#if> >
                </span>
                <span class="form-control">Platné osobné údaje (non expired)</span>
            </div>
        </div>
    </div>

    <br>

    <div class="row">
        <div class="form-group col-md-6 col-xs-6 col-sm-6">
            <label for="patient-autority">Autorita</label>
            <select name="autority" class="form-control" id="patient-autority">
                <#if employee.getStringAuthorities()?seq_contains("USER")>
                    <option value="USER" selected="selected">USER</option>
                    <option value="ADMIN">ADMIN</option>
                <#else>
                    <option value="USER">USER</option>
                    <option value="ADMIN" selected="selected">ADMIN</option>
                </#if>
            </select>
        </div>
    </div>

    <div class="form-group">
        <h3>Špecializácie</h3>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th style="width: 60px;">#</th>
                <th>Názov</th>
                <th style="width: 60px;">Odobrať</th>
            </tr>
            </thead>
            <tbody id="specializations">
                    <#list employee.specializations as specialization>
                    <tr id="specialization-${specialization.id}">
                        <td>${specialization_index+1}</td>
                        <td>${specialization.name}</td>
                        <td class="text-center">
                            <button name="submit" class="btn btn-sm btn-danger"
                                    onclick="removeSpecialization(${specialization.id})">
                                <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
                            </button>
                        </td>
                    </tr>
                    </#list>
            </tbody>
        </table>
        <h4 id="no-specializations"
            <#if employee.specializations?has_content>style="display: none"</#if>class="text-muted">
            Zamestnanec nemá priradené žiadne špecializácie.</h4>
    </div>
    <div class="input-group">
        <input type="text" class="form-control" id="searchTerm" placeholder="Vyhladať špecializáciu..."
               name="searchTerm">
        <span class="input-group-btn">
            <button id="searchButton" class="btn btn-default ladda-button" data-style="zoom-in" onclick="search()">
                <spanc lass="ladda-label">Hladať</span></button>
        </span>
    </div>
    <div style="display: none" id="search-container">
        <h3>Nájdené </h3>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th style="width: 60px;">#</th>
                <th>Názov</th>
                <th style="width: 60px;">Pridať</th>
            </tr>
            </thead>
            <tbody id="search-results">
            </tbody>
        </table>
        <h4 id="no-results" class="text-muted">Žiadne nájdené výsledky.</h4>
    </div>

    <div style="padding-top: 30px" class="form-group">
        <div>
            <button id="saveButton" class="btn btn-success ladda-button" data-style="zoom-in" onclick="save()">
        <span class="ladda-label">
            <span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>
            Uložiť
        </span>
            </button>
        </div>
    </div>
</div>
<script src="<@spring.url '/js/admin/employee.js'/>"></script>
</@pt.dashboardPage>