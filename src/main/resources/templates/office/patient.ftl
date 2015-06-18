<#-- @ftlvariable name="patient" type="sk.stuba.fei.team.local.domain.Patient" -->
<#import "../lib/pageTemplates.ftl" as pt>
<#import "/spring.ftl" as spring>
<@pt.menuFooterPage pageTitle="Detail pacienta">
<br>
<a class="btn btn-info btn-sm" href="<@spring.url '/office/${office_id}/orders'/>" role="button">Naspäť</a>

<div>
    <h1>Detail pacienta</h1>
    <br>

    <div class="row">
        <div class="col-md-2">Meno:</div>
        <div class="col-md-4">${patient.prefix_title!} ${patient.firstName!} ${patient.surname!} ${patient.suffix_title!}</div>
    </div>
    <h4>Kontakt:</h4>

    <div class="row">
        <div class="col-md-2">Email:</div>
        <div class="col-md-4">${patient.email!}</div>
    </div>
    <div class="row">
        <div class="col-md-2">Telefón:</div>
        <div class="col-md-4">${patient.phone!}</div>
    </div>
    <br>

    <h3>Zoznam objednávok</h3>
    <hr>
    <div class="row">
        <table class="table table-striped">
            <thead>
            <tr>
                <th style="width: 40px;">#</th>
                <th style="width: 125px;">Dátum</th>
                <th style="width: 100px;">Čas</th>
                <th style="text-align: left">Poznámka</th>
            </tr>
            </thead>
            <tbody>
                <#list patient.appointments as appointment>
                    <#if appointment.office.id == office_id>
                    <tr>
                        <td>${appointment_index + 1}</td>
                        <td>${appointment.date?substring(0,10)}</td>
                        <td><span>${appointment.intervalStart}</span></td>
                        <td style="text-align: left">${appointment.note}</td>
                    </tr>
                    </#if>
                </#list>
            </tbody>
        </table>
    </div>
</div>
<script src="<@spring.url '/js/office/hours.js'/>"></script>
</@pt.menuFooterPage>